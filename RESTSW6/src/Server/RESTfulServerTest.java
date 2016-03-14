package Server;

import AllClient.AllClient;
import CertificateHandler.CertificateHandler;
import SingleClient.Client;
import AllClient.Entry;
import AllClient.Locations;
import SingleClient.WirelessClientLocation;
import com.sun.net.httpserver.HttpServer;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Anders on 04-03-16.
 */
public class RESTfulServerTest {

    static String[] mainArgs = new String[3];
    static List<Entry> smallClientList = new ArrayList<Entry>();
    static Entry entry = new Entry();
    static Client client = new Client();
    static WirelessClientLocation wirelessClientLocation = new WirelessClientLocation();
    static Locations location = new Locations();
    static AllClient allClient = new AllClient();
    static HttpServer localServer;
    static String address,
            localIP = "127.0.0.1",
            wirelessCLientLocationDesc = "{\"macAddress\":\"01:02:03:04:05:06\"," +
            "\"currentlyTracked\":true,\"confidenceFactor\":10.0," +
            "\"ipAddress\":[\"01.02.03.456\"],\"ssId\":\"test\",\"band\":" +
            "\"UNKNOWN\",\"apMacAddress\":\"0a:0b:0c:0d:0e:0f\"," +
            "\"isGuestUser\":false,\"dot11Status\":\"ASSOCIATED\",\"MapInfo" +
            "\":{\"mapHierarchyString\":\"DevNetCampus>DevNetBuilding>DevNetZone" +
            "\",\"floorRefId\":723413320329068590,\"Dimension\":{\"length" +
            "\":81.9,\"width\":307.0,\"height\":16.5,\"offsetX\":0.0,\"" +
            "offsetY\":0.0,\"unit\":\"FEET\"}},\"MapCoordinate\":{\"x\"" +
            ":194.47,\"y\":51.8,\"unit\":\"FEET\"},\"Statistics\":{\"" +
            "currentServerTime\":\"2016-03-07T09:17:48.631+0000\",\"" +
            "firstLocatedTime\":\"2016-02-04T23:44:29.634+0000\",\"" +
            "lastLocatedTime\":\"2016-03-07T09:18:18.121+0000\",\"" +
            "additionalProperties\":{}},\"additionalProperties\":{}}",
            allClientDesc = "{\"Locations\":{\"totalPages\":1,\"currentPage\":1," +
            "\"pageSize\":80,\"entries\":[{\"macAddress\":\"01:02:03:04:05:06\"," +
            "\"currentlyTracked\":true,\"confidenceFactor\":10.0," +
            "\"ipAddress\":[\"01.02.03.456\"],\"ssId\":\"test\",\"band\":" +
            "\"UNKNOWN\",\"apMacAddress\":\"0a:0b:0c:0d:0e:0f\"," +
            "\"isGuestUser\":false,\"dot11Status\":\"ASSOCIATED\",\"MapInfo" +
            "\":{\"mapHierarchyString\":\"DevNetCampus>DevNetBuilding>DevNetZone" +
            "\",\"floorRefId\":723413320329068590,\"Dimension\":{\"length" +
            "\":81.9,\"width\":307.0,\"height\":16.5,\"offsetX\":0.0,\"" +
            "offsetY\":0.0,\"unit\":\"FEET\"}},\"MapCoordinate\":{\"x\"" +
            ":194.47,\"y\":51.8,\"unit\":\"FEET\"},\"Statistics\":{\"" +
            "currentServerTime\":\"2016-03-07T09:17:48.631+0000\",\"" +
            "firstLocatedTime\":\"2016-02-04T23:44:29.634+0000\",\"" +
            "lastLocatedTime\":\"2016-03-07T09:18:18.121+0000\",\"" +
            "additionalProperties\":{}},\"additionalProperties\":{}}]}}",
            clientDesc = "{\"WirelessClientLocation\":{\"macAddress\":\"" +
            "00:00:2a:01:00:0a\",\"currentlyTracked\":true,\"confidenceFactor" +
            "\":56.0,\"ipAddress\":[\"10.10.20.169\"],\"ssId\":\"test\",\"band" +
            "\":\"UNKNOWN\",\"apMacAddress\":\"00:2b:01:00:03:00\",\"isGuestUser" +
            "\":false,\"dot11Status\":\"ASSOCIATED\",\"MapInfo\":{\"" +
            "mapHierarchyString\":\"DevNetCampus>DevNetBuilding>DevNetZone\",\"" +
            "floorRefId\":723413320329068590,\"Dimension\":{\"length\":81.9,\"" +
            "width\":307.0,\"height\":16.5,\"offsetX\":0.0,\"offsetY\":0.0,\"" +
            "unit\":\"FEET\"}},\"MapCoordinate\":{\"x\":120.43,\"y\":40.03,\"unit" +
            "\":\"FEET\"},\"Statistics\":{\"currentServerTime\":\"" +
            "2016-03-09T11:12:15.259+0000\",\"firstLocatedTime\":\"" +
            "2016-02-04T23:44:29.635+0000\",\"lastLocatedTime\":\"" +
            "2016-03-09T11:12:09.963+0000\"}}}";



    @BeforeClass
    public static void setUp() throws Exception {
        System.out.printf("RESTfulServer tests initiated... \n\n");

        mainArgs[0] = localIP;
        mainArgs[1] = "test";
        mainArgs[2] = "works";
        address = "00:11:22:33:44:55";
        smallClientList.add(entry);
        wirelessClientLocation.setMacAddress("12:34:56:78:90:12");
        entry.setMacAddress("12:34:56:78:90:12");
        location.setEntries(smallClientList);
        allClient.setLocations(location);
        client.setWirelessClientLocation(wirelessClientLocation);
        try {
            localServer = HttpServer.create(new InetSocketAddress(localIP,
                            8080),
                    RESTfulServer.SizeofConnectionQueue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test // Not done yet
    public void testMain() throws Exception {
        RESTfulServer.main(mainArgs );
        assertEquals("Not implemented yet", true, false);
    }

    @Test
    public void testHttpGet() throws Exception {
        assertEquals("Not implemented yet", true, false);
    }

    @Test
    public void testAuthentication() throws Exception {
        assertEquals("Testing 'Authentication' with normal input",
                "Basic dGVzdDp3b3Jrcw==",
                RESTfulServer.Authentication("test", "works"));
        assertEquals("Testing 'Authentication' with messed up input",
                "Basic w6bDuMOlw7XDqV8gOycnPyHCpDrDn+KZo+KYuw==",
                RESTfulServer.Authentication("æøåõé_ ;''?!¤", "ß♣☻"));
        assertEquals("Testing 'Authentication' with no input",
                "Basic Og==",
                RESTfulServer.Authentication("", ""));
    }

    @Test // This test does not work as the lambda is trivially true
    public void testVerifyConnection() throws Exception {
        localServer.createContext("/api/contextaware/v1/location/clients", httpExchange -> {
            String response = RESTfulServer.CollectAllClients("", "", "https://");
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
            assertEquals("Testing 'VerifyConnection'", true,
                    RESTfulServer.VerifyConnection(httpExchange));
            });
        assertEquals("Not implemented yet", true, false);
    }

    @Test
    public void testObfuscateMacAddress() throws Exception {
        assertEquals("Testing 'ObfuscateMacAddress' with AllClient",
                "12:34:56", RESTfulServer.ObfuscateMacAddress(allClient)
                        .getLocations().getEntries().get(0).getMacAddress());
        assertEquals("Testing 'ObfuscateMacAddress' with SingleClient",
                "12:34:56",RESTfulServer.ObfuscateMacAddress(client)
                        .getWirelessClientLocation().getMacAddress());
        assertEquals("Testing 'ObfuscateMacAddress' with no input",
                "", "");
    }

    @Test
    public void testObfuscateEmail() throws Exception {
        //assertEquals("Testing 'ObfuscateMacAddress'", "anonymous OR null", RESTfulServer.ObfuscateEmail());
        // The actual method has not been implemented yet
    }

    @Test
    public void testAddMacAddressToWatchList() throws Exception {
        RESTfulServer.AddMacAddressToWatchList(address);
        RESTfulServer.AddMacAddressToWatchList("");
        assertEquals("Testing 'AddMacAddressToWatchList ' with a valid input",
                RESTfulServer.watchList.first(), address);
        assertEquals("Testing 'AddMacAddressToWatchList' with a invalid input (empty)",
                RESTfulServer.watchList.size(), 1);
    }

    @Test
    public void testRemoveMacAddressToWatchList() throws Exception {
        testAddMacAddressToWatchList();
        assertEquals("Testing 'RemoveMacAddressToWatchList' before removing the address",
                RESTfulServer.watchList.first(), address);
        RESTfulServer.RemoveMacAddressToWatchList(address);
        assertEquals("Testing 'RemoveMacAddressToWatchList' after removing the address",
                RESTfulServer.watchList.isEmpty(), true);
    }

    @Test
    public void testReadJsonToWirelessClientLocation() throws Exception{
        WirelessClientLocation premadeClient =
                RESTfulServer.ReadJsonToWirelessClientLocation(wirelessCLientLocationDesc);
        assertEquals("Testing 'ReadJsonToClient' MacAddress", "01:02:03:04:05:06",
                premadeClient.getMacAddress());
    }

    @Test
    public void testReadJsonToClient() throws Exception {
        Client premadeCLient1 = RESTfulServer.ReadJsonToClient(clientDesc);
        assertEquals("This test will always fail. Contact Anders",
                "00:00:2a:01:00:0a",
                premadeCLient1.getWirelessClientLocation().getMacAddress());
    }

    @Test
    public void testReadJsonToClientList() throws Exception {
        AllClient premadeAllClient = RESTfulServer.ReadJsonToClientList(allClientDesc);

        // This test comapres an Entry value (MacAddress)
        assertEquals("Testing 'ReadJsonToClient' MacAddress", "01:02:03:04:05:06",
                premadeAllClient.getLocations().getEntries().get(0).getMacAddress());
        // This test compares a location value (TotalPages)
        assertEquals("Testing 'ReadJsonToClient' TotalPages", 1,
                premadeAllClient.getLocations().getTotalPages().intValue());
    }

    @Test
    public void testConvertToJson() throws Exception {
        assertEquals("Testing 'ConvertToJson' with normal text",
                "\"This is Text\"", RESTfulServer.ConvertToJson("This is Text"));
        //assertEquals("Testing 'ConvertToJson' with normal text",
        //        "\"æøåõé_ ;'?!¤ß♣☻\"", RESTfulServer.ConvertToJson("æøåõé_ ;'?!¤ß♣☻"));
    }

    @Test
    public void testCollectSingleClient() throws Exception {
        localServer.createContext("/api/contextaware/v1/location/clients/12.34.56.789", httpExchange -> {
            httpExchange.sendResponseHeaders(200, allClientDesc.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(allClientDesc.getBytes(Charset.forName("UTF-8")));
            os.close();
        });
        localServer.start();
        System.out.println("Local server started at:");
        System.out.println(localServer.getAddress());

        RESTfulServer.CollectSingleClient("test", "works", "12.34.56.789","http://" + localIP);
    }

    @Test
    public void testCollectAllClients() throws Exception {
        new CertificateHandler();
        localServer.createContext("/api/contextaware/v1/location/clients", httpExchange -> {
            httpExchange.sendResponseHeaders(200, allClientDesc.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(allClientDesc.getBytes(Charset.forName("UTF-8")));
            os.close();
        });
        localServer.start();
        System.out.println("Local server started at:");
        System.out.println(localServer.getAddress());

       // RESTfulServer.CollectAllClients("test", "works", "http://" + localIP);

        System.in.read();
        System.out.println("Stopping server");
        localServer.stop(0);
        System.out.println("Server stopped");
    }
}
