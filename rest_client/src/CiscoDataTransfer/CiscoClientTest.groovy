package CiscoDataTransfer

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import static org.junit.Assert.assertEquals


/**
 * Created by Anders on 03-03-16.
 */
class CiscoClientTest {

    CiscoClient tester = new CiscoClient();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("CiscoClient tests initiated...\n");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("CiscoClient tests finished...");
    }

    @Test
    // This method have not been tested yet
    void testHttpGet() {
       // Assert.assertEquals("testing?", tester.httpGet("","",""), 2);
    }

    @Test
    // Tests if the encoding method calls functions as expected
    void testAuthentication() {
        assertEquals("Testing 'Authentication'", "Basic dGVzdDp3b3Jrcw==",
                tester.Authentication("test", "works"));

    }

    @Test
    // This method is not tested as it only calls an external libary
    void testReadJsonToClient() {
        // Trivia true
    }

    @Test
    // This method is not tested as it only calls an external libary
    void testReadJsonToClientList() {
        // Trivia true
    }

    @Test
    // This method is not tested as it only calls an external libary
    void testConvertToJson() {
        // Trivia true
    }
}
