package CiscoDataTransfer

import AllClient.AllClient
import org.junit.AfterClass
import org.junit.Test
import org.junit.BeforeClass

import static org.junit.Assert.assertEquals

/**
 * Created by Anders on 03-03-16.
 */
class CiscoPullerTest {
    CiscoPuller tester = new CiscoPuller();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("CiscoPuller tests initiated...\n");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("CiscoPuller tests finished...");
    }

    @Test
    // This method have not been tested yet. OBS it is a void method
    void testRun() {
        //assertEquals("Testing 'function', cond1, cond2)
    }

    @Test
    //
    void testGetCiscoData() {
        //assertEquals("Testing 'function', cond1, cond2)
    }

    @Test
    // Tests if the data was properly sent to the database
    void testSendToDB1() {
        // This test method tests both versions of 'SendToDB'
        AllClient clients = new AllClient();
        assertEquals("Testing 'SendToDB' with a AllClient", true,
                tester.SendToDB(clients));
        assertEquals("Testing 'SendToDB' with a String", true,
                tester.SendToDB("Some data in the right format"));
    }
}
