package Server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CertificateHandler.*;
import AllClient.*;
import SingleClient.*;

import diff_match_patchpack.diff_match_patch;


public class RESTfulServer {

    private static String ciscoIp; /** This is the ip we get the data from*/
    protected static String myIp; /** The user ip aka my ip*/
    private static String username; /** username */
    private static String password; /**password */

    protected static final int port = 8080;
    protected static final int SizeofConnectionQueue = 1;

    // Gets the host address. Might cause trouble if several or no addresses returned.
    static {
        try {
            myIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("Unknown Host Address");
            e.printStackTrace();
        }
    }

    protected static TreeSet<String> watchList = new TreeSet<String>();

    public static void main(String[] args) throws IOException {
        new CertificateHandler();
        if (args.length != 3){
            System.out.println("Invalid arguments. Please run the program with the arguments: Cisco-IP Username Password");
            System.out.println("Example: 64.103.26.61 admin admin");
            return;
        }
        ciscoIp = "https://" + args[0];
        username = args[1];
        password = args[2];
        try {
            httpGet(ciscoIp + "/api/contextaware/v1/location/clients/", username, password);
        }
        catch(IOException e){
            System.out.println("Invalid url, username or password");
            return;
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(myIp, port), SizeofConnectionQueue);

        // To add support for another CISCO MSE API call, add another context with the server.createContext() call.
        // Supported CISCO MSE restful API can be found here: http://www.cisco.com/c/en/us/td/docs/wireless/mse/3350/7-5/MSE_REST_API/Guide/Cisco_MSE_REST_API_Guide/Location_API.html

        // CreateContext adds a context to the restful service.
        // In this case it will allow the path http://IP/api/contextaware/v1/location/clients
        // The anonymous method is an overwrite of HttpHandler, an interface requiring implementation of a handle method.
        server.createContext("/api/contextaware/v1/location/clients", httpExchange -> {
            if (!VerifyConnection(httpExchange)){
                return;
            }

            /// HERE IS RESPONSE
            String response = CollectAllClients(username, password, ciscoIp);
            System.out.println("Response:  " + response);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
        });

        server.createContext("/api/contextaware/v1/location/clients/", httpExchange -> {
            if (!VerifyConnection(httpExchange)){
                return;
            }
            // Finds the difference between the URL entered and the one created with this context.
            // If an url such as ip/api/contextaware/v1/location/clients/00:00:00:00:00:00 is entered, the diff
            // will be the mac address. This is used to query Cisco MSE.
            diff_match_patch diff = new diff_match_patch();
            LinkedList<diff_match_patch.Diff> diffLinkedList = diff.diff_main(httpExchange.getRequestURI().getPath(),
                                                                              httpExchange.getHttpContext().getPath());


            /// HERE IS RESPONSE
            String response = CollectSingleClient(username, password,diffLinkedList.peekLast().text, ciscoIp);
            System.out.println("Response: " + response);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
        });

        // Adds a mac address to the watchlist. This address is not obfuscated untill it is removed from the watchlist.
        server.createContext("/api/watchlist/add/", httpExchange -> {
            if (!VerifyConnection(httpExchange)){
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

        // Removes a mac address from the watchlist. After this it will be obfuscated.
        server.createContext("/api/watchlist/remove/", httpExchange -> {
            if (!VerifyConnection(httpExchange)){
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

        // Simple test to see if server is online. Only used for debugging.
        server.createContext("/online", httpExchange -> {
           /*  if (!VerifyConnection(httpExchange)){
                return;
            }*/

            System.out.println("Received request from " + httpExchange.getRemoteAddress().getAddress());
            String response = "We are ONLINE!" ;
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

    /**
     * Makes the connection to Cisco.
     * @param urlStr The url to get the data from (ip + /api/contextaware/v1/location/clients/)
     * @param userName a valid username recognised by us
     * @param userPW a valid password paired with a username
     * @return the collected data in stringform
     * @throws java.io.IOException if response code is not 200
     */
    protected static String httpGet(String urlStr, String userName, String userPW) throws java.io.IOException {
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

    /**
     * Method to check login information
     * @param name a valid username recognised by us
     * @param password a valid password paired with a username
     * @return if the login was successful or not
     */
    protected static String Authentication(String name, String password) {
        String temp = name + ":" + password;
        Base64.Encoder enc = Base64.getEncoder();
        String result = enc.encodeToString(temp.getBytes());
        return "Basic " + result;
    }

    /**
     * Method to test connection
     * @param httpExchange  All the information about connection, user ip, request, server ip
     * @return true if valid login and false if not
     * @throws IOException if wrong username, password combo
     */
    // Verifies a HTTP request by examining the basic auth header string and IP.
    // If auth header isn't correct the connection is closed.
    protected static boolean VerifyConnection(HttpExchange httpExchange) throws IOException {
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
        // TODO test IP address!
        return true;
    }

    /**
     * Method to make all MAC-Addresses unrecognizable
     * @param allList takes in all the data
     * @return half the MAC-Address
     */
    //Keep a list of mac-addresses that we are allowed to track.
    //This code simply uses a SortedSet of addresses, it checks every entry and if we have to obfuscate, we remove the first half.
    protected static AllClient ObfuscateMacAddress(AllClient allList) {
        for (Entry item : allList.getLocations().getEntries()) {
            String oldMacAddress = item.getMacAddress();

            if (watchList.contains(oldMacAddress)) {
                continue;
            }
            item.setMacAddress(oldMacAddress.substring(0, oldMacAddress.length() / 2));
        }
        return allList;
    }

    /**
     * Method to hide MAC-Address of the single user you are looking for
     * @param singleClient Gets the one users info and halfs the MAC-Address
     * @return the updated info for the choosen user
     */
    protected static Client ObfuscateMacAddress(Client singleClient) {
        String oldMacAddress = singleClient.getWirelessClientLocation().getMacAddress();
        if (!watchList.contains(oldMacAddress)) {
            singleClient.getWirelessClientLocation().setMacAddress(oldMacAddress.substring(0, oldMacAddress.length() / 2));
        }
        return singleClient;
    }

    protected static void ObfuscateEmail(){
        // TODO IMPLEMENT THIS!
    }

    /**
     * Method that adds the MAC-Address to the list of known MAC-Addresses
     * @param macaddress It takes in a MAC-Address
     */
    public static void AddMacAddressToWatchList(String macaddress) {
        Pattern macVerifier = Pattern.compile("[0-9a-f]{2}([-:])[0-9a-f]{2}(\\1[0-9a-f]{2}){4}$");
        Matcher matcher = macVerifier.matcher(macaddress);
        Boolean match = matcher.matches();
        if (match && !watchList.contains(macaddress))
            watchList.add(macaddress);
    }

    /**
     * Methos to remove a MAC-Address form the list of MAC-Addresses
     * @param macaddress takes in the detected MAC-Address
     */
    public static void RemoveMacAddressToWatchList(String macaddress) {
        //Here we need to verify that the input parameter is a valid address.
        if(watchList.contains(macaddress))
            watchList.remove(macaddress);
        else {
            System.out.println("Mac-address '" + macaddress + "' is invalid");
        }
    }

    /**
     * Method that
     * Takes Json data and puts in into a Client object, see {@link SingleClient.Client}
     * @param json the data from Cisco in Json form
     * @return a Client with parameters assigned
     */
    // Convert json string to a Java class.
    protected static Client ReadJsonToClient(String json){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Client.class);
    }

    /**
     * As {@link RESTfulServer#ReadJsonToClient(String)} but directly to {@link SingleClient.WirelessClientLocation}
     * @param json the data from Cisco in Json form
     * @return a WirelessClientLocation with parameters assigned
     */
    // Convert json string to a Java class.
    protected static WirelessClientLocation ReadJsonToWirelessClientLocation(String json){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, WirelessClientLocation.class);
    }

    /**
     *  Takes Json data and puts in into a AllClient object, see {@link AllClient}
     * @param json the data from Cisco in Json form
     * @return the locatins for all clients
     */
    // Convert json string to a Java class.
    protected static AllClient ReadJsonToClientList(String json){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, AllClient.class);
    }

    /**
     * Method that uses an external library to convert data to Json
     * @param classOfT a var such that it takes anything but intended for string
     * @param <T> is a generic class
     * @return the converted data
     */
    protected static <T> String ConvertToJson(T classOfT){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(classOfT);
    }

    /**
     * Method to connect to RESTful service, get response
     * and convert to object, obfuscate MAC-address and print.
     * see {@link Server.RESTfulServer#CollectAllClients(String, String, String)}
     * @param username a valid username recognised by us and bound to a password
     * @param password a valid password paired with a username
     * @param userID the MAC-Address of the user you wish to find
     * @param ip the ip from where to get the data
     * @return returns the data on the user you found
     * @throws IOException if incorrect username password combination
     */
    public static String CollectSingleClient(String username, String password, String userID, String ip) throws IOException {
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients/" + userID, username, password);
        Client client = ReadJsonToClient(requestresult);
        client = ObfuscateMacAddress(client);
        return ConvertToJson(client);
    }

    /**
     * Method to connect to RESTful service, get response
     * and convert to object, obfuscate MAC-address and print.
     * @param username a valid username recognised by us
     * @param password a valid password paired with a username
     * @param ip    the ip from where to get the data
     * @return the collected data in Json form
     * @throws IOException throws exception from GetHttp() if response code is not 200
     */
    public static String CollectAllClients(String username, String password, String ip) throws IOException {
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients/",
                username, password);
        AllClient allClient = ReadJsonToClientList(requestresult);
        allClient = ObfuscateMacAddress(allClient);
        return ConvertToJson(allClient);
    }

}
