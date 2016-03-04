package Server;

import AllClient.AllClient;
import SingleClient.Client;
import AllClient.Entry;
import AllClient.Locations;
import SingleClient.WirelessClientLocation;
import com.sun.net.httpserver.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Anders on 04-03-16.
 */
public class RESTfulServerTest {

    public int testnum = 0;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.printf("RESTfulServer tests initiated... \n\n");
    }

    @After
    public void tearDown() throws Exception {
        testnum = testnum + 1;
        System.out.printf("RESTfulServer test %d finished \n", testnum);
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
        HttpServer server = HttpServer.create(new InetSocketAddress(RESTfulServer.myIp, RESTfulServer.port),
                RESTfulServer.SizeofConnectionQueue);
        server.createContext("/api/contextaware/v1/location/clients", httpExchange -> {
            String response = RESTfulServer.CollectAllClients("", "", "https://");
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
            assertEquals("Testing 'VerifyConnection'", true, RESTfulServer.VerifyConnection(httpExchange));
            });
    }

    @Test
    public void testObfuscateMacAddress() throws Exception {
        Entry client = new Entry();
        client.setMacAddress("12:34:56:78:90:12");
        List<Entry> smallClientList = new ArrayList<Entry>(){{
            add(client);
        }};

        Locations location = new Locations();
        AllClient allClient = new AllClient();
        allClient.setLocations(location);
        allClient.getLocations().setEntries(smallClientList);

        String newAddress = RESTfulServer.ObfuscateMacAddress(allClient)
                .getLocations().getEntries().get(0).getMacAddress();
        assertEquals("Testing 'ObfuscateMacAddress' with AllClient",
                "12:34:56", newAddress);
    }

    @Test
    public void testObfuscateMacAddress1() throws Exception {
        Client client = new Client();
        WirelessClientLocation wirelessClientLocation = new WirelessClientLocation();
        client.setWirelessClientLocation(wirelessClientLocation);
        client.getWirelessClientLocation().setMacAddress("12:34:56:78:90:12");
        assertEquals("Testing 'ObfuscateMacAddress' with SingleClient",
                "12:34:56",RESTfulServer.ObfuscateMacAddress(client).getWirelessClientLocation().getMacAddress());
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
    public void testReadJsonToClient() throws Exception {
        //assertEquals("Testing 'function', cond1, cond2)
    }

    @Test
    public void testReadJsonToClientList() throws Exception {
        //assertEquals("Testing 'function', cond1, cond2)
    }

    @Test
    public void testConvertToJson() throws Exception {
        //assertEquals("Testing 'function', cond1, cond2)
    }

    @Test
    public void testCollectSingleClient() throws Exception {
        //assertEquals("Testing 'function', cond1, cond2)
    }

    @Test
    public void testCollectAllClients() throws Exception {
        //assertEquals("Testing 'function', cond1, cond2)
    }
}