package Server;
import AllClient.AllClient;
import AllClient.Entry;
import CertificateHandler.CertificateHandler;
import SingleClient.Client;
import SingleClient.WirelessClientLocation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import diff_match_patchpack.diff_match_patch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static staticMethods.staticMethods.*;

/**
 * This class is used to retrieve data from a Cisco restfull service, further more it obfuscate the mac-addresses if
 * there are not white listed, it is build as a restfull service to emulate the same behaviour as the Cisco service.
 */

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
            myIp = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
           System.out.println("Unknown Host Address");
           e.printStackTrace();
        }
    }

    protected static TreeSet<String> watchList = new TreeSet<String>();

    public static void main(String[] args) throws IOException {
        new CertificateHandler();
        if (args.length != 3) {
            System.out.println("Invalid arguments. Please run the program with the arguments: Cisco-IP Username Password");
            System.out.println("Example: 64.103.26.61 admin admin");
            return;
        }
        ciscoIp = "http://" + args[0];
        username = args[1];
        password = args[2];
        /*if (httpGet(ciscoIp + "/online", username, password) == null) {
            try {
                httpGet(ciscoIp + "/api/contextaware/v1/location/clients/", username, password);
            } catch (Exception e) {
                System.out.println("Invalid url, username or password");
                return;
            }
        }*/

            HttpServer server = HttpServer.create(new InetSocketAddress(myIp, port)
                    , SizeofConnectionQueue);

            // To add support for another CISCO MSE API call, add another context with the server.createContext() call.
            // Supported CISCO MSE restful API can be found here: http://www.cisco.com/c/en/us/td/docs/wireless/mse/3350/7-5/MSE_REST_API/Guide/Cisco_MSE_REST_API_Guide/Location_API.html

            // CreateContext adds a context to the restful service.
            // In this case it will allow the path http://IP/api/contextaware/v1/location/clients
            // The anonymous method is an overwrite of HttpHandler, an interface requiring implementation of a handle method.
            server.createContext("/api/contextaware/v1/location/clients", httpExchange -> {
                if (!VerifyConnection(httpExchange)) {
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
                if (!VerifyConnection(httpExchange)) {
                    return;
                }
                // Finds the difference between the URL entered and the one created with this context.
                // If an url such as ip/api/contextaware/v1/location/clients/00:00:00:00:00:00 is entered, the diff
                // will be the mac address. This is used to query Cisco MSE.
                diff_match_patch diff = new diff_match_patch();
                LinkedList<diff_match_patch.Diff> diffLinkedList = diff.diff_main(httpExchange.getRequestURI().getPath(),
                        httpExchange.getHttpContext().getPath());


                /// HERE IS RESPONSE
                String response = CollectSingleClient(username, password, diffLinkedList.peekLast().text, ciscoIp);
                System.out.println("Response: " + response);

                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes(Charset.forName("UTF-8")));
                os.close();
            });

            // Adds a mac address to the watchlist. This address is not obfuscated untill it is removed from the watchlist.
            server.createContext("/api/watchlist/add/", httpExchange -> {
                if (!VerifyConnection(httpExchange)) {
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
                if (!VerifyConnection(httpExchange)) {
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
                String response = "We are ONLINE!";
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

}
