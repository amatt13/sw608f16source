package CiscoDataTransfer;


import AllClient.AllClient;

import java.io.IOException;
import static CiscoDataTransfer.CiscoClient.httpGet;


/**
 * Created by Anders on 22-02-16.
 */
public class CiscoPuller extends Thread {

    @Override public void run() {
        long time;
        while(true) {
            time = System.currentTimeMillis();
            String data = GetCiscoData();
            System.out.println("Pulled Data at " + System.currentTimeMillis());
            // >>Convert to a Java Class?<<
            //AllClient CiscoDataTransfer = CiscoClient.ReadJsonToClientList(data);
            // >>Convert to a more readable string?<<
            //data = CiscoDataTransfer.getLocations().getEntries().toString();

            SendToDB(data);

            try {
                sleep(time + 300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public String GetCiscoData() {
        String data = "null";
        try {
            System.out.println(
                    data = httpGet("http://172.26.120.61:8080/api/contextaware/v1/location/clients",
                    "test", "works"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private Boolean SendToDB (String data) {
        try {
            // some send function
            return true;
        } catch ( Exception e/*Some error*/) {
            e.printStackTrace();
            return false;
        }
    }

    private Boolean SendToDB (AllClient data) {
        try {
            // some send function
            return true;
        } catch ( Exception e/*Some error*/) {
            e.printStackTrace();
            return false;
        }
    }
}
