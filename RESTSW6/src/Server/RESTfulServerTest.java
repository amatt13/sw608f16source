package Server;

import AllClient.AllClient;
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

    static String localIP = "127.0.0.1";
    static Entry entry = new Entry();
    static Client client = new Client();
    static WirelessClientLocation wirelessClientLocation = new WirelessClientLocation();
    static Locations location = new Locations();
    static AllClient allClient = new AllClient();
    static List<Entry> smallClientList = new ArrayList<Entry>(){{
        add(entry);
    }};
    static HttpServer localServer;
    String clientDesc = "{\"macAddress\":\"01:02:03:04:05:06\"," +
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
            "additionalProperties\":{}},\"additionalProperties\":{}}";
    String allClientDesc = "{\"Locations\":{\"totalPages\":1,\"currentPage\":1," +
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
            "additionalProperties\":{}},\"additionalProperties\":{}}]}}";



    @BeforeClass
    public static void setUp() throws Exception {
        System.out.printf("RESTfulServer tests initiated... \n\n");

        entry.setMacAddress("12:34:56:78:90:12");
        location.setEntries(smallClientList);
        allClient.setLocations(location);
        wirelessClientLocation.setMacAddress("12:34:56:78:90:12");
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

    @Test
    public void testMain() throws Exception {
        //assertEquals("Testing 'function', cond1, cond2)
    }

    @Test
    public void testHttpGet() throws Exception {
        //assertEquals("Testing 'function', cond1, cond2)
    }

    @Test
    public void testAuthentication() throws Exception {
        assertEquals("Testing 'Authentication'", "Basic dGVzdDp3b3Jrcw==",
                RESTfulServer.Authentication("test", "works"));
    }

    @Test
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
    }

    @Test
    public void testObfuscateMacAddress() throws Exception {
        assertEquals("Testing 'ObfuscateMacAddress' with AllClient",
                "12:34:56", RESTfulServer.ObfuscateMacAddress(allClient)
                        .getLocations().getEntries().get(0).getMacAddress());
    }

    @Test
    public void testObfuscateMacAddress1() throws Exception {
        assertEquals("Testing 'ObfuscateMacAddress' with SingleClient",
                "12:34:56",RESTfulServer.ObfuscateMacAddress(client)
                        .getWirelessClientLocation().getMacAddress());
    }

    @Test
    public void testObfuscateEmail() throws Exception {
        //assertEquals("Testing 'ObfuscateMacAddress'", "anonymous OR null", RESTfulServer.ObfuscateEmail());
    }

    @Test
    public void testAddMacAddressToWatchList() throws Exception {
        try {
            RESTfulServer.AddMacAddressToWatchList("00:11:22:33:44:55");
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testRemoveMacAddressToWatchList() throws Exception {
        try {
            RESTfulServer.RemoveMacAddressToWatchList("00:11:22:33:44:55");
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadJsonToWirelessClientLocation() throws Exception{
        WirelessClientLocation premadeClient = RESTfulServer.ReadJsonToWirelessClientLocation(clientDesc);
        assertEquals("Testing 'ReadJsonToClient' MacAddress", "01:02:03:04:05:06",
                premadeClient.getMacAddress());
    }

    @Test
    public void testReadJsonToClient() throws Exception {

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
        // intentionally not tested
    }

    @Test
    public void testCollectSingleClient() throws Exception {
        //
    }

    @Test
    public void testCollectAllClients() throws Exception {
        localServer.createContext("/api/contextaware/v1/location/clients", httpExchange -> {
            httpExchange.sendResponseHeaders(200, allClientDesc.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(allClientDesc.getBytes(Charset.forName("UTF-8")));
            os.close();
        });
        localServer.start();
        System.out.println("Local server started at:");
        System.out.println(localServer.getAddress());

        RESTfulServer.CollectAllClients("test", "works", "http://" + localIP);
    }
}