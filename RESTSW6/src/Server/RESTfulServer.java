package Server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import CertificateHandler.*;
import AllClient.*;
import SingleClient.*;

import diff_match_patchpack.diff_match_patch;


public class RESTfulServer {

    private static final String ciscoIp = "https://64.103.26.61";
    private static final String myIp = "172.26.120.105";

    private static TreeSet<String> macAddressDB = new TreeSet<String>();

    public static void main(String[] args) throws IOException {
        new CertificateHandler();
        HttpServer server = HttpServer.create(new InetSocketAddress(myIp, 8080), 1);
        server.createContext("/api/contextaware/v1/location/clients", httpExchange -> {
            if (VerifyConnection(httpExchange) == false){
                return;
            }

            /// HERE IS RESPONSE
            String response = CollectAllClients("admin", "admin", ciscoIp);
            System.out.println("Response:  " + response);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            PrintWriter printer = new PrintWriter(os, true);
            printer.write(response);
            os.close();
        });

        server.createContext("/api/contextaware/v1/location/clients/", httpExchange -> {
            if (VerifyConnection(httpExchange) == false){
                return;
            }
            diff_match_patch diff = new diff_match_patch();
            LinkedList<diff_match_patch.Diff> diffLinkedList = diff.diff_main(httpExchange.getRequestURI().getPath(),
                                                                              httpExchange.getHttpContext().getPath());


            /// HERE IS RESPONSE
            String response = CollectSingleClient("admin", "admin",diffLinkedList.peekLast().text, ciscoIp);
            System.out.println("Response: " + response);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
        });

        server.createContext("/api/watchlist/add/", httpExchange -> {
            if (VerifyConnection(httpExchange) == false){
                return;
            }
            diff_match_patch diff = new diff_match_patch();
            LinkedList<diff_match_patch.Diff> diffLinkedList = diff.diff_main(httpExchange.getRequestURI().getPath(),
                                                                              httpExchange.getHttpContext().getPath());
            System.out.println(diffLinkedList.peekLast().text);


            String response = "Added " + diffLinkedList.peekLast().text + " to watchlist.\n Go to " +
                              server.getAddress() + "/api/watchlist/remove/" +
                              diffLinkedList.peekLast().text + " to remove from watchlist.";
            AddMacAddressToWatchList(diffLinkedList.peekLast().text);
            System.out.println("Response: " + response);
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
        });

        server.createContext("/api/watchlist/remove/", httpExchange -> {
            if (VerifyConnection(httpExchange) == false){
                return;
            }
            diff_match_patch diff = new diff_match_patch();
            LinkedList<diff_match_patch.Diff> diffLinkedList = diff.diff_main(httpExchange.getRequestURI().getPath(),
                                                                              httpExchange.getHttpContext().getPath());
            System.out.println(diffLinkedList.peekLast().text);


            String response = "Removed " + diffLinkedList.peekLast().text +
                              " from watchlist.\n Go to " + server.getAddress() +
                              "/api/watchlist/add/" + diffLinkedList.peekLast().text + " to add to watchlist.";
            RemoveMacAddressToWatchList(diffLinkedList.peekLast().text);
            System.out.println("Response: " + response);
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
        });

        server.start();

        System.out.println("Server running");
        System.out.println("Hit return to stop...");
        System.out.println(server.getAddress());
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

    private static String httpGet(String urlStr, String userName, String userPW) throws java.io.IOException {
        URL url = new URL(urlStr);
        //String ip = getIP();
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();
        //Request type
        conn.setRequestMethod("GET");
        //Request headers
        conn.setRequestProperty("Authorization", Authentication(userName, userPW));
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();
        return sb.toString();
    }

    private static String Authentication(String name, String password) {
        String temp = name + ":" + password;
        Base64.Encoder enc = Base64.getEncoder();
        String result = enc.encodeToString(temp.getBytes());
        return "Basic " + result;
    }

    private static boolean VerifyConnection(HttpExchange httpExchange) throws IOException {
        System.out.println("Received request from " + httpExchange.getRemoteAddress().getAddress());

        Headers headers = httpExchange.getRequestHeaders();
        List<String> passphrase = headers.get("Authorization");
        if (passphrase == null || !passphrase.get(0).equals("Basic dGVzdDp3b3Jrcw==")){ // test:works
            httpExchange.sendResponseHeaders(200, 0);
            OutputStream os = httpExchange.getResponseBody();
            String response = "Cannot authenticate. Wrong username + password combo.";
            System.out.println(response);
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
            return false;
        }
        // TEST IP ADDRESS HERE!!!
        return true;
    }

    //Here we have the choice to make a key-value object and store all mac-addresses with a unique value.
    //Alternatively we can keep a list of mac-addresses that we are allowed to track.
    //This code simply uses a SortedSet of addresses, it checks every entry and if we have to obfuscate we remove the first half.
    private static AllClient ObfuscateMacAddress(AllClient allList) {
        for (Entry item : allList.getLocations().getEntries()) {
            String oldMacAddress = item.getMacAddress();

            if (macAddressDB.contains(oldMacAddress)) {
                continue;
            }
            item.setMacAddress(oldMacAddress.substring(0, oldMacAddress.length() / 2));
        }
        return allList;
    }

    private static Client ObfuscateMacAddress(Client singleClient) {
        String oldMacAddress = singleClient.getWirelessClientLocation().getMacAddress();
        if (!macAddressDB.contains(oldMacAddress)) {
            singleClient.getWirelessClientLocation().setMacAddress(oldMacAddress.substring(0, oldMacAddress.length() / 2));
        }
        return singleClient;
    }

    public static void AddMacAddressToWatchList(String macaddress) {
        //Here we need to verify that the input parameter is a valid address.
        if (!macAddressDB.contains(macaddress))
            macAddressDB.add(macaddress);
    }

    public static void RemoveMacAddressToWatchList(String macaddress) {
        //Here we need to verify that the input parameter is a valid address.
        if(macAddressDB.contains(macaddress))
            macAddressDB.remove(macaddress);
    }

    // Convert json string to a Java class.
    private static Client ReadJsonToClient(String json){
        Gson gson = new GsonBuilder().create();
        Client client = gson.fromJson(json, Client.class);
        return client;
    }

    // Convert json string to a Java class.
    private static AllClient ReadJsonToClientList(String json){
        Gson gson = new GsonBuilder().create();
        AllClient clientlist = gson.fromJson(json, AllClient.class);
        return clientlist;
    }


    private static <T> String ConvertToJson(T classOfT){
        Gson gson = new GsonBuilder().create();
        String result = gson.toJson(classOfT);
        return result;
    }

    // Method to cconnect to RESTful service, get response and convert to object, obfuscate MAC-address and print.
    public static String CollectSingleClient(String username, String password, String userID, String ip) throws IOException {
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients/" + userID, username, password);
        Client client = ReadJsonToClient(requestresult);
        client = ObfuscateMacAddress(client);
        String result = ConvertToJson(client);
        return result;
    }

    // Method to cconnect to RESTful service, get response and convert to object, obfuscate MAC-address and print.
    public static String CollectAllClients(String username, String password, String ip) throws IOException {
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients/", username, password);
        AllClient allClient = ReadJsonToClientList(requestresult);
        allClient = ObfuscateMacAddress(allClient);
        String result = ConvertToJson(allClient);
        return result;
    }


}
