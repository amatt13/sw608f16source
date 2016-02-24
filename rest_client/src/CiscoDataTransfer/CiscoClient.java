package CiscoDataTransfer;

import AllClient.AllClient;
import SingleClient.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import com.google.gson.*;

/**
 * Created by Anders on 22-02-16.
 */
public class CiscoClient {
  public static void main(String[] argv) {
      CiscoPuller ciscoPuller = new CiscoPuller();

      // This thread pulls data from Cisco and stores it on the Database
      ciscoPuller.start();
  }

    // Pull data from VPN server
    protected static String httpGet(String urlStr, String userName, String userPW) throws java.io.IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization", Authentication(userName, userPW));
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();
        return sb.toString();
    }

    private static String Authentication(String name, String password) {
        String temp = name + ":" + password;
        Base64.Encoder enc = Base64.getEncoder();
        String temp2 = enc.encodeToString(temp.getBytes());
        return "Basic " + temp2;
    }

    // Convert json string to a Java class.
    private static Client ReadJsonToClient(String json){
        Gson gson = new GsonBuilder().create();
        return  gson.fromJson(json, Client.class);
    }

    // Convert json string to a Java class.
    protected static AllClient ReadJsonToClientList(String json){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, AllClient.class);
    }

    protected static <T> String ConvertToJson(T classOfT){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(classOfT);
    }

}
