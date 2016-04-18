package staticMethods;

import Client.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    static Client singleClient = new Client();
    static WirelessClientLocation wirelessClientLocation = new WirelessClientLocation();
    static Locations location = new Locations();
    static AllClient allClient = new AllClient();
    static HttpServer localServer;
    static String address,
            localIP,
            wirelessCLientLocationDesc = "{\"macAddress\":\"1c:4b:d6:a7:3a:86\"," +
            "\"currentlyTracked\":true,\"confidenceFactor\":96.0," +
            "\"ipAddress\":[\"172.28.146.171\",\"fe80:0000:0000:0000:8435:38c3:2098:0a41\"]," +
            "\"userName\":\"mmures14@student.aau.dk\",\"ssId\":\"AAU-1x\"," +
            "\"band\":\"UNKNOWN\",\"apMacAddress\":\"6c:9c:ed:ec:5d:a0\"," +
            "\"isGuestUser\":false,\"dot11Status\":\"ASSOCIATED\"," +
            "\"MapInfo\":{\"mapHierarchyString\":\"Sohngaardsholmsvej>SGV57L>Stueplan\"," +
            "\"floorRefId\":-6045715975825784107,\"Dimension\":{\"length\":377.8," +
            "\"width\":155.5,\"height\":10.0,\"offsetX\":0.0,\"offsetY\":0.0," +
            "\"unit\":\"FEET\"},\"Image\":{\"imageName\":\"domain_0_1280490386919.png\"}}," +
            "\"MapCoordinate\":{\"x\":83.74,\"y\":329.13,\"unit\":\"FEET\"}," +
            "\"Statistics\":{\"currentServerTime\":\"2016-03-30T14:36:02.630+0200\"," +
            "\"firstLocatedTime\":\"2016-03-30T09:35:30.308+0200\"," +
            "\"lastLocatedTime\":\"2016-03-30T14:34:58.988+0200\"}," +
            "\"GeoCoordinate\":{\"latitude\":57.0273061896,\"longitude\":9.9472380576," +
            "\"unit\":\"DEGREES\"}}",
            allClientDesc = "{\"Locations\":{\"totalPages\":1,\"currentPage\":1,\"pageSize\":80," +
                    "\"entries\":[{\"macAddress\":\"1c:4b:d6:a7:3a:86\",\"currentlyTracked\":true," +
                    "\"confidenceFactor\":96.0,\"ipAddress\":[\"172.28.146.171\"," +
                    "\"fe80:0000:0000:0000:8435:38c3:2098:0a41\"],\"userName\":" +
                    "\"mmures14@student.aau.dk\",\"ssId\":\"AAU-1x\",\"band\":\"UNKNOWN\"," +
                    "\"apMacAddress\":\"6c:9c:ed:ec:5d:a0\",\"isGuestUser\":false," +
                    "\"dot11Status\":\"ASSOCIATED\",\"MapInfo\":{\"mapHierarchyString\"" +
                    ":\"Sohngaardsholmsvej>SGV57L>Stueplan\",\"floorRefId\":-6045715975825784107," +
                    "\"Dimension\":{\"length\":377.8,\"width\":155.5,\"height\":10.0,\"offsetX\":0.0," +
                    "\"offsetY\":0.0,\"unit\":\"FEET\"},\"Image\":" +
                    "{\"imageName\":\"domain_0_1280490386919.png\"}},\"MapCoordinate\":{\"x\":83.74," +
                    "\"y\":329.13,\"unit\":\"FEET\"},\"Statistics\":{\"currentServerTime\":" +
                    "\"2016-03-30T14:36:02.630+0200\",\"firstLocatedTime\"" +
                    ":\"2016-03-30T09:35:30.308+0200\",\"lastLocatedTime\"" +
                    ":\"2016-03-30T14:34:58.988+0200\"},\"GeoCoordinate\":{\"latitude\":57.0273061896," +
                    "\"longitude\":9.9472380576,\"unit\":\"DEGREES\"}}]}}",
            clientDesc = "{\"WirelessClientLocation\":{\"macAddress\":\"1c:4b:d6:a7:3a:86\"," +
                    "\"currentlyTracked\":true,\"confidenceFactor\":96.0," +
                    "\"ipAddress\":[\"172.28.146.171\",\"fe80:0000:0000:0000:8435:38c3:2098:0a41\"]," +
                    "\"userName\":\"mmures14@student.aau.dk\",\"ssId\":\"AAU-1x\"," +
                    "\"band\":\"UNKNOWN\",\"apMacAddress\":\"6c:9c:ed:ec:5d:a0\"," +
                    "\"isGuestUser\":false,\"dot11Status\":\"ASSOCIATED\"," +
                    "\"MapInfo\":{\"mapHierarchyString\":\"Sohngaardsholmsvej>SGV57L>Stueplan\"," +
                    "\"floorRefId\":-6045715975825784107,\"Dimension\":{\"length\":377.8," +
                    "\"width\":155.5,\"height\":10.0,\"offsetX\":0.0,\"offsetY\":0.0," +
                    "\"unit\":\"FEET\"},\"Image\":{\"imageName\":\"domain_0_1280490386919.png\"}}," +
                    "\"MapCoordinate\":{\"x\":83.74,\"y\":329.13,\"unit\":\"FEET\"}," +
                    "\"Statistics\":{\"currentServerTime\":\"2016-03-30T14:36:02.630+0200\"," +
                    "\"firstLocatedTime\":\"2016-03-30T09:35:30.308+0200\"," +
                    "\"lastLocatedTime\":\"2016-03-30T14:34:58.988+0200\"}," +
                    "\"GeoCoordinate\":{\"latitude\":57.0273061896,\"longitude\":9.9472380576," +
                    "\"unit\":\"DEGREES\"}}}";

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
        assertEquals("Testing 'Obfuscate' with AllClient",
                "12:34:56", Obfuscate(allClient)
                        .getLocations().getEntries().get(0).getMacAddress());
        assertEquals("Testing 'Obfuscate' with SingleClient",
                "12:34:56", Obfuscate(client)
                        .getWirelessClientLocation().getMacAddress());
        assertEquals("Testing 'Obfuscate' with no input",
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
        assertEquals("Testing 'ReadJsonToClient' MacAddress", "1c:4b:d6:a7:3a:86",
                premadeClient.getMacAddress());
    }

    @Test
    public void testReadJsonToClientList() throws Exception {
        AllClient premadeAllClient = ReadJsonToClientList(allClientDesc);

        // This test compares an Entry value (MacAddress)
        assertEquals("Testing 'ReadJsonToClient' MacAddress", "1c:4b:d6:a7:3a:86",
                premadeAllClient.getLocations().getEntries().get(0).getMacAddress());
        // This test compares a location value (TotalPages)
        assertEquals("Testing 'ReadJsonToClient' TotalPages", 1,
                premadeAllClient.getLocations().getTotalPages().intValue());
    }

    @Test
    public void testReadJsonToClient() throws Exception {
        Client premadeCLient1 = ReadJsonToClient(clientDesc);
        assertEquals("Testing 'ReadJsonToClient'",
                "1c:4b:d6:a7:3a:86",
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
        //assertEquals("Testing 'Obfuscate'", "anonymous OR null", RESTfulServer.ObfuscateEmail());
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
        String description = CollectSingleClient("test", "works", "12.34.56.789",
                "http://127.0.0.1:8080");
        singleClient = ReadJsonToClient(description);
        assertEquals("Testing 'CollectSingleClient'",
                singleClient.getWirelessClientLocation().getApMacAddress(),
                "6c:9c:ed:ec:5d:a0");

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(singleClient);

        assertEquals("Testing 'CollectSingleClient' See if the class " +
                "will be converted into the starting string'"
                , description, json);
    }

    @Test
    public void testCollectAllClients() throws Exception {
        String description = CollectAllClients("test", "works", "http://127.0.0.1:8080");
        allClient = ReadJsonToClientList(description);
        assertEquals("Testing 'CollectAllClients'", allClient.getLocations().getEntries().get(0)
                .getApMacAddress(), "6c:9c:ed:ec:5d:a0");

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(allClient);

        assertEquals("Testing 'CollectSingleClient' See if the class " +
                        "will be converted into the starting string'"
                , description, json);
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