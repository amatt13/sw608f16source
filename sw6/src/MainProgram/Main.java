package MainProgram;

import CertificateHandler.CertificateHandler;
import SingleClient.*;
import AllClient.*;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.littleshoot.proxy.*;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.littleshoot.proxy.impl.ClientToProxyConnection;

public class Main {

    private static String ip;
    private static TreeSet<String> macAddressDB = new TreeSet<String>();


    public static void main(String[] argv) throws IOException {
        ip = "https://64.103.26.61";
        new CertificateHandler();
        //CollectSingleClient("admin","admin","00:00:2a:01:00:05", "https://64.103.26.61");
        //CollectAllClients("admin", "admin", "https://64.103.26.61");


        //System.out.println(httpGet("https://64.103.26.61/api/contextaware/v1/location/clients", "admin", "admin"));

        //String test1 = httpGet("https://64.103.26.61/api/contextaware/v1/location/clients", "admin", "admin");
        //String test2 = httpGet("https://64.103.26.61/api/contextaware/v1/location/clients/00:00:2a:01:00:05", "admin", "admin");

        HttpFiltersSourceAdapter bob = new HttpFiltersSourceAdapter() {
            public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
                return new HttpFiltersAdapter(originalRequest) {
                    @Override
                    public HttpResponse clientToProxyRequest(HttpObject httpObject) {
                        //System.out.println(originalRequest);
                        //System.out.println(httpObject);
                        DefaultHttpRequest test = (DefaultHttpRequest)httpObject;

                        System.out.println(test.headers().get("Authorization"));

                        String test1 = null;
                        try {
                            test1 = httpGet("https://64.103.26.61/api/contextaware/v1/location/clients", "admin", "admin");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        DefaultFullHttpResponse bob1 = new DefaultFullHttpResponse(new HttpVersion("HTTP", 1, 0, false), new HttpResponseStatus(200, test1), Unpooled.copiedBuffer("??: " + "FUCK"
                                + "\r\n", CharsetUtil.UTF_8) );
                        return bob1;
                    }

                    @Override
                    public HttpObject serverToProxyResponse(HttpObject httpObject) {
                        // TODO: implement your filtering here
                        return httpObject;
                    }
                };
            }
        };
        ProxyAuthenticator auth = new ProxyAuthenticator() {
            public boolean authenticate(String userName, String password) {
                System.out.println("Usernamepw: " + userName + "   " + password);
                if (userName.equals("test") && password.equals("works"))
                    return true;
                return true;
            }
        };

        HttpProxyServer server =
                DefaultHttpProxyServer.bootstrap().withAddress(new InetSocketAddress("10.0.0.2", 8080)).withFiltersSource(bob).withTransparent(true).
                        start();

        System.out.println(server.getListenAddress());

    }

    private static String httpGet(String urlStr, String userName, String userPW) throws java.io.IOException {

        URL url = new URL(urlStr);
        //String ip = getIP();
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization", Authentication(userName, userPW));
        conn.setRequestMethod("GET");
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

    private static String getIP() throws IOException {
        System.out.print("Enter IP-address: ");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        String address;
        try {
            address = buffer.readLine();
        } catch (IOException ioe) {
            address = "0.0.0.0";
            System.out.println("IO error trying to read address");
        }
        return "https://" + address;
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
    public static void CollectSingleClient(String username, String password, String userID, String ip) throws IOException {
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients/" + userID, username, password);
        Client client = ReadJsonToClient(requestresult);
        client = ObfuscateMacAddress(client);
        System.out.println(ConvertToJson(client));

        //Here we need to decide if we send a java object, a string or a json to the DB.
    }

    // Method to cconnect to RESTful service, get response and convert to object, obfuscate MAC-address and print.
    public static void CollectAllClients(String username, String password, String ip) throws IOException {
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients/", username, password);
        AllClient allClient = ReadJsonToClientList(requestresult);
        allClient = ObfuscateMacAddress(allClient);
        System.out.println(ConvertToJson(allClient));
        //Here we need to decide if we send a java object, a string or a json to the DB.
    }
}