package staticMethods;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import AllClient.*;
import Client.*;
import CertificateHandler.CertificateHandler;

/**
 * Created by Simon on 16/03/2016.
 */
public class staticMethods {

    public static TreeSet<String> watchList = new TreeSet<String>();

    /**
     * Method to test connection
     * @param httpExchange  All the information about connection, user ip, request, server ip
     * @return true if valid login and false if not
     * @throws IOException if wrong username, password combo
     */
    public static boolean VerifyConnection(HttpExchange httpExchange) throws IOException {
        System.out.println("Received request from " + httpExchange.getRemoteAddress().getAddress());

        Headers headers = httpExchange.getRequestHeaders();
        List<String> passphrase = headers.get("Authorization");
        if (passphrase == null || !passphrase.get(0).equals("Basic dGVzdDp3b3Jrcw==")){ // test:works
            httpExchange.sendResponseHeaders(401, 0);
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
     * Method to verify login information
     * @param httpExchange an instance og HttpExchange
     * @return true if information ok and false if not
     * @throws IOException through httpExchange
     */
    public static boolean VerifyHeaders(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getRequestHeaders();
        List<String> username = headers.get("Username");
        List<String> passphrase = headers.get("Password");
        List<String> ip = headers.get("url");
        if (passphrase == null || username == null|| ip == null){ // test:works
            httpExchange.sendResponseHeaders(401, 0);
            OutputStream os = httpExchange.getResponseBody();
            String response = "Please submit a Username header with a username and a Password header with a password.";
            System.out.println(response);
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
            return false;
        }
        return true;
    }

    /**
     * Method to hide sensitive info of the single user you are looking for
     * @param singleClient contains the data for the user
     * @return the updated info for the chosen user
     */
    public static Client Obfuscate(Client singleClient) {
        String oldMacAddress = singleClient.getWirelessClientLocation().getMacAddress();
        if (!watchList.contains(oldMacAddress)) {
            singleClient.getWirelessClientLocation().setMacAddress("OBFUSCATED");
            singleClient.getWirelessClientLocation().setUserName("OBFUSCATED");
        }
        return singleClient;
    }

    /**
     * Method to make all sensitive information unrecognizable
     * @param allList takes in all the data
     * @return the modified AllClient instance with sensitive information replaced
     */
    public static AllClient Obfuscate(AllClient allList) {

        for (Entry item : allList.getLocations().getEntries()) {
            String oldMacAddress = item.getMacAddress();

            if (watchList.contains(oldMacAddress)) {
                continue;
            }
            item.setMacAddress("OBFUSCATED");
            item.setUserName("OBFUSCATED");
        }
        return allList;
    }

    /**
     * Method to check login information
     * @param name a valid username recognised by us
     * @param password a valid password paired with a username
     * @return if the login was successful or not
     */
    public static String Authentication(String name, String password) {
        String temp = name + ":" + password;
        Base64.Encoder enc = Base64.getEncoder();
        String temp2 = enc.encodeToString(temp.getBytes());
        return "Basic " + temp2;
    }

    /**
     * Takes Json data and puts in into a Client object
     * @param json the data from Cisco in Json form
     * @return a Client with parameters assigned
     */
    // Convert json string to a Java class.
    public static WirelessClientLocation ReadJsonToWirelessClientLocation(String json){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, WirelessClientLocation.class);
    }

    /**
     *  Takes Json data and puts in into a AllClient object
     * @param json the data from Cisco in Json form
     * @return the locatins for all clients
     */
    // Convert json string to a Java class.
    public static AllClient ReadJsonToClientList(String json){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, AllClient.class);
    }

    /**
     * Takes Json data and puts in into a Client object
     * @param json the data from Cisco in Json form
     * @return a Client with parameters assigned
     */
    public static Client ReadJsonToClient(String json){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Client.class);
    }

    /**
     * Method that uses an external library to convert data to Json
     * @param classOfT a var such that it takes anything but intended for string
     * @param <T> is a generic class
     * @return the converted data
     */
    public static <T> String ConvertToJson(T classOfT){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(classOfT);
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
     * @param macaddress MAC-address of the found user
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
     * Method to connect to RESTful service, get response
     * and convert to object, obfuscate MAC-address and print.
     * see {@link #CollectAllClients(String, String, String)}
     * @param username a valid username recognised by us and bound to a password
     * @param password a valid password paired with a username
     * @param userID the MAC-Address of the user you wish to find
     * @param ip the ip from where to get the data
     * @return returns the data on the user you found
     * @throws IOException through {@link #httpGet}
     */
    public static String CollectSingleClient(String username, String password, String userID, String ip) throws IOException {
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients/" + userID, username, password);
        if(requestresult == null){
            System.out.println("Invalid user.");
            throw new IOException();
        }
        Client client = ReadJsonToClient(requestresult);
        client = Obfuscate(client);
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
    public static String CollectAllClients(String username, String password, String ip, String page, String pageSize) throws IOException {
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients",
                username, password, page, pageSize);
        AllClient allClient = ReadJsonToClientList(requestresult);
        allClient = Obfuscate(allClient);
        return ConvertToJson(allClient);
    }
    public static String CollectAllClients(String username, String password, String ip) throws IOException {
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients",
                username, password);
        AllClient allClient = ReadJsonToClientList(requestresult);
        allClient = Obfuscate(allClient);
        return ConvertToJson(allClient);
    }

    /**
     * Makes the connection to Cisco.
     * @param urlStr The url to get the data from (ip + /api/contextaware/v1/location/clients/)
     * @param userName a valid username recognised by us
     * @param userPW a valid password paired with a username
     * @return the collected data in stringform
     * @throws java.io.IOException if response code is not 200
     */
    public static String httpGet(String urlStr, String userName, String userPW) throws java.io.IOException {
        URL url = new URL(urlStr);
        //String ip = getIP();
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();
        //Request type
        conn.setRequestMethod("GET");
        //Request headers
        conn.setRequestProperty("Authorization", Authentication(userName, userPW));
        conn.setRequestProperty("Accept", "application/json");
        //conn.setRequestProperty("page", "1");
        //conn.setRequestProperty("pageSize", "50000");

        int msg = conn.getResponseCode();
        if ( msg!= 200) {
            System.out.println("Error when trying to connect: http " + msg);
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
     * Method to check if connection was successful
     * @param urlStr the URL to connect to
     * @return true if connection successful false if not
     */
    public static boolean IsServerOnline(String urlStr) {
        new CertificateHandler();
        try {
            URL url = new URL(urlStr);
            //String ip = getIP();
            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();
            //Request type
            conn.setRequestMethod("GET");
            //Request headers

            int i = conn.getResponseCode();
            // Buffer the result into a string

            conn.disconnect();
            return true;
        }
        catch(MalformedURLException e){
            System.out.println("Invalid url.");
            return false;
        }
        catch(IOException e){
            System.out.println("Could not connect to the URL.");
            return false;
        }
    }

    /**
     *
     * @param urlStr URL we connect to
     * @param userName a valid username recognised by us
     * @param userPW  a password corresponding to the username
     * @param ciscourl URL for Cisco
     * @param ciscoUser Cisco username
     * @param ciscopw Cisco password
     * @return data extracted form Cisco
     * @throws java.io.IOException if responseCode is not 200
     */
    public static String httpPost(String urlStr, String userName, String userPW,
                                  String ciscourl, String ciscoUser, String ciscopw) throws java.io.IOException {
        new CertificateHandler();
        URL url = new URL(urlStr);
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();
        //Request type
        conn.setRequestMethod("POST");
        //Request headers
        conn.setRequestProperty("Authorization", Authentication(userName, userPW));
        conn.setRequestProperty("Username", ciscoUser);
        conn.setRequestProperty("Password", ciscopw);
        conn.setRequestProperty("url", ciscourl);
        conn.setRequestProperty("Accept", "application/json");

        int msg = conn.getResponseCode();
        if ( msg != 200) {
            System.out.println(msg);
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
     * Method that checks if the ip format is valid
     * Courtesy of Mat Banik http://stackoverflow.com/questions/4581877/validating-ipv4-string-in-java
     * @param ip URL you try to connect to
     * @return true if the URL is valid false if not
     */
    public static boolean validIP(String ip) {
        if (ip == null || ip.isEmpty()) return false;
        ip = ip.trim();
        if ((ip.length() < 6) & (ip.length() > 22)) return false;

        try {
            Pattern patternWithPort = Pattern.compile("^(http)(s)?(://)(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(:([0-9]{1,5})$)?");
            Matcher matcherWithPort = patternWithPort.matcher(ip);

            return matcherWithPort.matches();
        } catch (PatternSyntaxException ex) {
            return false;
        }
    }

    /**
     * Method that checks if the password is valid
     * @param pw the input password
     * @return the password
     */
    public static String identifyPW(String pw) {
        if (pw == null || pw.isEmpty()) return null;
        try {
            String[] split = pw.split(":");
            if (split[0].equals("Password")) {

                return split[1].trim();
            }
            return null;
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * Methos to verify username
     * @param username the input username
     * @return the username
     */
    public static String identifyUser(String username) {
        if (username == null || username.isEmpty()) return null;
        try {
            String[] split = username.split(":");
            if (split[0].equals("Username")) {
                return split[1].trim();
            }
            return null;
        }
        catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * Makes the connection to Cisco.
     * @param urlStr The url to get the data from (ip + /api/contextaware/v1/location/clients/)
     * @param userName a valid username recognised by us
     * @param userPW a valid password paired with a username
     * @return the collected data in stringform
     * @throws java.io.IOException if response code is not 200
     */
    public static String httpGet(String urlStr, String userName, String userPW, String pageNumber, String pageSize) throws java.io.IOException {
        URL url = new URL(urlStr+"?page="+pageNumber+"&pageSize="+pageSize);
        //String ip = getIP();
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();
        //Request type
        conn.setRequestMethod("GET");
        //Request headers
        //conn.setRequestProperty("page", pageNumber);
        //conn.setRequestProperty("pageSize", pageSize);
        conn.setRequestProperty("Authorization", Authentication(userName, userPW));
        conn.setRequestProperty("Accept", "application/json");

        int msg = conn.getResponseCode();
        if ( msg!= 200) {
            System.out.println("Error when trying to connect: http " + msg);
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
}
