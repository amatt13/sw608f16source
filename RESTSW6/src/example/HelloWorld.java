package example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.deploy.util.StringUtils;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Base64;
import java.util.LinkedList;
import java.util.TreeSet;

import CertificateHandler.*;
import AllClient.*;
import SingleClient.*;

import diff_match_patchpack.diff_match_patch;

/**
 * Created by Simon on 22/02/2016.
 */
// The Java class will be hosted at the URI path "/hello"
public class HelloWorld {

    private static String ip;
    private static TreeSet<String> macAddressDB = new TreeSet<String>();

    public static void main(String[] args) throws IOException {
        new CertificateHandler();
        HttpServer server = HttpServer.create(new InetSocketAddress("172.26.120.105", 8080), 1);
        server.createContext("/api/contextaware/v1/location/clients", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                System.out.println("Received request from " + httpExchange.getRemoteAddress().getAddress());
                /// HERE IS RESPONSE
                String response = CollectAllClients("admin", "admin", "https://64.103.26.61");

                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                PrintWriter printer = new PrintWriter(os);
                printer.write(response);
                os.close();
            }
        });

        server.createContext("/api/contextaware/v1/location/clients/", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {

                diff_match_patch bo = new diff_match_patch();
                LinkedList<diff_match_patch.Diff> l = bo.diff_main(httpExchange.getRequestURI().getPath(),httpExchange.getHttpContext().getPath());
                System.out.println(l.peekLast().text);
                /// HERE IS RESPONSE

                String response = CollectSingleClient("admin", "admin",l.peekLast().text, "https://64.103.26.61");
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os2 = httpExchange.getResponseBody();
                PrintWriter printer2 = new PrintWriter(os2);
                printer2.write(response);
                printer2.write("kkkkkkkkk");
                os2.close();
            }
        });

        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://172.26.120.105:8080/hello");
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
        conn.setRequestProperty("Authorization", Authentication(userName, userPW));
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        /*if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }*/

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
        String temp2 = enc.encodeToString(temp.getBytes());
        return "Basic " + temp2;
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
        macAddressDB.add(macaddress);
    }

    public static void RemoveMacAddressToWatchList(String macaddress) {
        //Here we need to verify that the input parameter is a valid address.
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
        String bob = gson.toJson(classOfT);
        return bob;
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
