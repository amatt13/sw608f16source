package CiscoDataTransfer;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anders on 08-03-16.
 */
public class CiscoClientTest {

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.printf("CiscoClient tests initiated... \n\n");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMain() throws Exception {

    }

    @Test
    public void testHttpGet() throws Exception {

    }

    @Test
    public void testAuthentication() throws Exception {
        assertEquals("Testing 'Authentication'", "Basic dGVzdDp3b3Jrcw==",
                CiscoClient.Authentication("test", "works"));
    }
}