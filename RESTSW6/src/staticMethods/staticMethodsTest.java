package staticMethods;

import SingleClient.Client;
import SingleClient.WirelessClientLocation;
import com.sun.net.httpserver.HttpServer;
import AllClient.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static staticMethods.staticMethods.*;

/**
 * Created by Anders on 16-03-16.
 */
public class staticMethodsTest {

    static int SizeofConnectionQueue;
    static String[] mainArgs = new String[3];
    static List<Entry> smallClientList = new ArrayList<Entry>();
    static Entry entry = new Entry();
    static Client client = new Client();
    static Client httpClient = new Client();
    static WirelessClientLocation wirelessClientLocation = new WirelessClientLocation();
    static Locations location = new Locations();
    static AllClient allClient = new AllClient();
    static AllClient httpAllClient = new AllClient();
    static HttpServer localServer;
    static String address,
            localIP,
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
    static public void setUp() throws Exception {
        System.out.printf("RESTfulServer tests initiated... \n\n");

        localIP = "127.0.0.1";
        mainArgs[0] = localIP;
        mainArgs[1] = "test";
        mainArgs[2] = "works";
        address = "00:11:22:33:44:55";
        smallClientList.add(entry);
        wirelessClientLocation.setMacAddress("12:34:56:78:90:12");
        entry.setMacAddress("12:34:56:78:90:12");
        location.setEntries(smallClientList);
        allClient.setLocations(location);
        SizeofConnectionQueue = 1;
        client.setWirelessClientLocation(wirelessClientLocation);
        try {
            localServer = HttpServer.create(new InetSocketAddress(localIP,
                            8080),
                    SizeofConnectionQueue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        localServer.createContext("/api/contextaware/v1/location/clients/12.34.56.789", httpExchange -> {
            httpExchange.sendResponseHeaders(200, clientDesc.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(clientDesc.getBytes());
            os.close();
        });
        localServer.createContext("/api/contextaware/v1/location/clients", httpExchange -> {
            httpExchange.sendResponseHeaders(200, allClientDesc.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(allClientDesc.getBytes());
            os.close();
        });
        localServer.setExecutor(null);
        localServer.start();
    }

    @AfterClass
    static public void tearDown() throws Exception {
        localServer.stop(0);
    }

    @Test
    public void testObfuscateMacAddress() throws Exception {
        assertEquals("Testing 'ObfuscateMacAddress' with AllClient",
                "12:34:56", ObfuscateMacAddress(allClient)
                        .getLocations().getEntries().get(0).getMacAddress());
        assertEquals("Testing 'ObfuscateMacAddress' with SingleClient",
                "12:34:56", ObfuscateMacAddress(client)
                        .getWirelessClientLocation().getMacAddress());
        assertEquals("Testing 'ObfuscateMacAddress' with no input",
                "", "");
    }

    @Test
    public void testVerifyConnection() throws Exception {
        localServer.createContext("/VERIFY", httpExchange -> {
            httpExchange.sendResponseHeaders(200, "Can you see me?".length());
            OutputStream os = httpExchange.getResponseBody();
            os.write("Can you see me?".getBytes());
            os.close();
            assertEquals("Testing 'VerifyConnection'", true, VerifyConnection(httpExchange));
        });
    }

    @Test
    public void testAuthentication() throws Exception {
        assertEquals("Testing 'Authentication'", "Basic dGVzdDp3b3Jrcw==",
                Authentication("test", "works"));
    }

    @Test
    public void testReadJsonToWirelessClientLocation() throws Exception {
        WirelessClientLocation premadeClient =
                ReadJsonToWirelessClientLocation(wirelessCLientLocationDesc);
        assertEquals("Testing 'ReadJsonToClient' MacAddress", "01:02:03:04:05:06",
                premadeClient.getMacAddress());
    }

    @Test
    public void testReadJsonToClientList() throws Exception {
        AllClient premadeAllClient = ReadJsonToClientList(allClientDesc);

        // This test compares an Entry value (MacAddress)
        assertEquals("Testing 'ReadJsonToClient' MacAddress", "01:02:03:04:05:06",
                premadeAllClient.getLocations().getEntries().get(0).getMacAddress());
        // This test compares a location value (TotalPages)
        assertEquals("Testing 'ReadJsonToClient' TotalPages", 1,
                premadeAllClient.getLocations().getTotalPages().intValue());
    }

    @Test
    public void testReadJsonToClient() throws Exception {
        Client premadeCLient1 = ReadJsonToClient(clientDesc);
        assertEquals("Testing 'ReadJsonToClient'",
                "00:00:2a:01:00:0a",
                premadeCLient1.getWirelessClientLocation().getMacAddress());
    }

    @Test
    public void testConvertToJson() throws Exception {
        assertEquals("Testing 'ConvertToJson' with normal text",
                "\"This is Text\"", ConvertToJson("This is Text"));
        //assertEquals("Testing 'ConvertToJson' with weird text",
        //        "\"æøåõé_ ;'?!¤ß♣☻\"", RESTfulServer.ConvertToJson("æøåõé_ ;'?!¤ß♣☻"));
    }

    @Test
    public void testObfuscateEmail() throws Exception {
        //assertEquals("Testing 'ObfuscateMacAddress'", "anonymous OR null", RESTfulServer.ObfuscateEmail());
        // The actual method has not been implemented yet
    }

    @Test
    public void testAddMacAddressToWatchList() throws Exception {
        AddMacAddressToWatchList(address);
        AddMacAddressToWatchList("");

        assertEquals("Testing 'AddMacAddressToWatchList ' with a valid input",
                watchList.first(), address);
        assertEquals("Testing 'AddMacAddressToWatchList' with a invalid input (empty)",
                watchList.size(), 1);
    }

    @Test
    public void testRemoveMacAddressToWatchList() throws Exception {
        testAddMacAddressToWatchList();
        assertEquals("Testing 'RemoveMacAddressToWatchList' before removing the address",
                watchList.first(), address);
        RemoveMacAddressToWatchList(address);
        assertEquals("Testing 'RemoveMacAddressToWatchList' after removing the address",
                watchList.isEmpty(), true);
    }

    @Test
    public void testCollectSingleClient() throws Exception {
        String description = CollectSingleClient("test", "works", "12.34.56.789", "http://127.0.0.1:8080");
        httpClient = ReadJsonToClient(description);
        assertEquals("Testing 'CollectSingleClient'",
                httpClient.getWirelessClientLocation().getApMacAddress(),
                "00:2b:01:00:03:00");
    }

    @Test
    public void testCollectAllClients() throws Exception {
        String description = CollectAllClients("test", "works", "http://127.0.0.1:8080");
        httpAllClient = ReadJsonToClientList(description);
        assertEquals("Testing 'CollectAllClients'", httpAllClient.getLocations().getEntries().get(0)
                .getApMacAddress(), "0a:0b:0c:0d:0e:0f");
    }

    @Test
    public void testHttpGet() throws Exception {
        String result = httpGet("http://localhost:8080/api/contextaware/v1/location/clients/12.34.56.789",
                "test", "works");
        assertEquals("Testing 'HttpGet''", clientDesc, result);
    }

    @Test
    public void testIdentifyPW() throws Exception {
        String password = "Password:bob";
        String result = identifyPW(password);
        assertEquals("Testing 'identifyPW'1", "bob", result);

        password = "Passwor:bob";
        result = identifyPW(password);
        assertEquals("Testing 'identifyPW'2", null, result);

        password = "Password bob";
        result = identifyPW(password);
        assertEquals("Testing 'identifyPW'3", null, result);

        password = "Password:  bob";
        result = identifyPW(password);
        assertEquals("Testing 'identifyPW'4", "bob", result);

        password = "bob";
        result = identifyPW(password);
        assertEquals("Testing 'identifyPW'5", null, result);
    }

    @Test
    public void testIdentifyUser() throws Exception {
        String username = "Username:bob";
        String result = identifyUser(username);
        assertEquals("Testing 'identifyUsername1'", "bob", result);

        username = "Usrname:bob";
        result = identifyUser(username);
        assertEquals("Testing 'identifyUsername2'", null, result);

        username = "Username: bob";
        result = identifyUser(username);
        assertEquals("Testing 'identifyUsername3'", "bob", result);

        username = "Usrname bob";
        result = identifyUser(username);
        assertEquals("Testing 'identifyUsername4'",null, result);

        username = "bob";
        result = identifyUser(username);
        assertEquals("Testing 'identifyUsername5'",null, result);
    }

    @Test
    public void testValidIP() throws Exception {
        String ip = "https://127.0.0.1";
        boolean result = validIP(ip);
        assertTrue("Testing 'validIP'1", result);

        ip = "https://127.0.0.1:8080";
        result = validIP(ip);
        assertTrue("Testing 'validIP'2", result);

        ip = "http://127.0.0.1:8080";
        result = validIP(ip);
        assertTrue("Testing 'validIP'3", result);

        ip = "127.0.0.1";
        result = validIP(ip);
        assertFalse("Testing 'validIP'4", result);

        ip = "https://127.0.0:8080";
        result = validIP(ip);
        assertFalse("Testing 'validIP'5", result);

        ip = "https://127.0.0.1:8080000000";
        result = validIP(ip);
        assertFalse("Testing 'validIP'6", result);

        ip = "https://127.0.:8080";
        result = validIP(ip);
        assertFalse("Testing 'validIP'7", result);
    }
}