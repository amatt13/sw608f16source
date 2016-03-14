package CiscoDataTransfer;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anders on 08-03-16.
 */
public class CiscoPullerTest {

    public static String password,
            user ,
            target;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.printf("CiscoPuller tests initiated... \n\n");
        password = "works";
        user = "test";
        target = "http://127.0.0.1:8080/api/contextaware/v1/location/clients";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRun() throws Exception {
        // Intentionally not tested
        // The method is trivial
        // CiscoDataTransfer.CiscoPuller.run()
    }

    @Test
    public void testContinuesPuller1() throws Exception {
        // This should be tested on a local server
        // It is currently tested on the online server
        assertEquals("Testing 'ContinuesPuller' with a positive integer", true,
                CiscoPuller.ContinuesPuller(10));
        assertEquals("Testing 'ContinuesPuller' with a negative integer", true,
                CiscoPuller.ContinuesPuller(-1));
        assertEquals("Testing 'ContinuesPuller' with 0 value integer", true,
                CiscoPuller.ContinuesPuller(0));
    }

    @Test
    public void testContinuesPuller2() throws Exception {
        // This should be tested on a local server
        // It is currently tested on the online server
        assertEquals("Testing 'ContinuesPuller' with 0 arguments", true,
                CiscoPuller.ContinuesPuller());
    }

    @Test
    public void testGetCiscoData() throws Exception {
        // This should be tested on a local server
        // The server is not started yet
        assertEquals("Testing GetCiscoData", "expected result",
                CiscoPuller.GetCiscoData(target, user, password));
    }

    @Test
    public void testSendToDB1() throws  Exception {
        // Actual function is not implemente
        // CiscoDataTransfer.CiscoPuller.SendToDB(java.lang.String)
    }

    @Test
    public void testSendToDB2() throws  Exception {
        // Actual function is not implemented
        // CiscoDataTransfer.CiscoPuller.SendToDB(AllClient.AllClient)
    }
}