package CiscoDataTransfer;


import AllClient.AllClient;
import com.sun.prism.paint.Stop;

import java.io.IOException;
import java.util.Objects;

import static CiscoDataTransfer.CiscoClient.httpGet;


/**
 * Created by Anders on 22-02-16.
 */
public class CiscoPuller extends Thread {

    @Override public void run() {
        if(!ContinuesPuller()) {
            System.out.println("It was not possible to pull data from the Cisco server\n" +
                    "Is the server running?");
        }
    }

    // Pulls data from the Cisco server. If numberOfPulls is null, the server will pull until stopped
    protected static Boolean ContinuesPuller(int numberOfPulls)  {
         Boolean pulledUntilStop = true;
        for (; numberOfPulls > 0; numberOfPulls--) {
            pulledUntilStop = ContinuesPullerHelper();
            if (!pulledUntilStop) return  false;
        }
        return pulledUntilStop;
    }

    protected static Boolean ContinuesPuller()  {
         try {
             while (ContinuesPullerHelper());
         }
         catch (Exception e){
             e.printStackTrace();
             return false;
         }
        return false;
    }

    private static Boolean ContinuesPullerHelper (){
        long time = System.currentTimeMillis();
        // Pull data via httpGEt
        String password = "works",
                user = "test",
                target = "http://172.26.120.61:8080/api/contextaware/v1/location/clients",
                data = GetCiscoData(target, user, password);

        if (!Objects.equals(data, "null")) {
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
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }

    protected static String GetCiscoData(String target, String user, String password) {
        String data = "null";
        try {
            System.out.println(
                    data = httpGet(target, user, password));
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return data;
    }

    protected static Boolean SendToDB (String data) {
        try {
            // some send function
            return true;
        } catch ( Exception e/*Some error*/) {
            e.printStackTrace();
            return false;
        }
    }

    protected static Boolean SendToDB (AllClient data) {
        try {
            // some send function
            return true;
        } catch ( Exception e/*Some error*/) {
            e.printStackTrace();
            return false;
        }
    }
}
