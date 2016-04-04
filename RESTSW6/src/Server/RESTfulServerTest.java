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
import static staticMethods.staticMethods.*;

/**
 * Created on 04-03-16.
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
}
