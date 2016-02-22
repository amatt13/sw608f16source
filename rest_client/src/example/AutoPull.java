package example;

import java.io.IOException;
import static example.CiscoClient.httpGet;
import static example.CiscoClient.ReadJsonToClientList;
import AllClient.AllClient;

/**
 * Created by Anders on 22-02-16.
 */
public class AutoPull extends Thread {

    public void run(){
        String sb = "null";
        boolean noErrors = true;
        long time;

        do {
            time = System.currentTimeMillis();
            try {
                sb = httpGet("http://172.26.120.105:8080/api/contextaware/v1/location/clients",
                        "name", "password");
                //send sb to DB
            } catch (IOException e) {
                noErrors = false;
                e.printStackTrace();
            }

            System.out.println("Pulled Data at " + System.currentTimeMillis());
            AllClient clients = ReadJsonToClientList(sb);
            try {
                sleep(time += 300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (noErrors);
    }
}
