package sample;

import CertificateHandler.CertificateHandler;
import JsonClasses.*;
import ClientList.*;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class Main {

    private static String ip;

    public static void main(String[] argv) throws IOException {
        ip = getIP();
        CertificateHandler certhandle = new CertificateHandler();

        System.out.println(httpGet("https://64.103.26.61/api/contextaware/v1/location/clients", "admin", "admin"));

        String bob = httpGet("https://64.103.26.61/api/contextaware/v1/location/clients", "admin", "admin");
        String bob1 = httpGet("https://64.103.26.61/api/contextaware/v1/location/clients/00:00:2a:01:00:05", "admin", "admin");


        Gson gson = new GsonBuilder().create();
        GetAll p = gson.fromJson(bob, GetAll.class);


        Client m = RequestClient("00:00:2a:01:00:05");
    }

    public static String httpGet(String urlStr, String userName, String userPW) throws java.io.IOException {

        URL url = new URL(urlStr);
        //String ip = getIP();
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization", authentication(userName, userPW));
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

    public static String getIP() throws IOException {
        System.out.print("Enter IP-address: ");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        String address;
        try {
           address = buffer.readLine();
        } catch (IOException ioe) {
            address = "0.0.0.0";
            System.out.println("IO error trying to read address");
        }
        return "https://" + address;
    }

    public static String authentication(String name, String password){
        String temp = name + ":" + password;
        Base64.Encoder enc = Base64.getEncoder();
        String temp2 = enc.encodeToString(temp.getBytes());
        return "Basic " + temp2;
    }

    public static Client RequestClient(String mcAddressOrIP) throws IOException {

        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients/" + mcAddressOrIP, "admin", "admin");
        Gson gson = new GsonBuilder().create();
        Client result = gson.fromJson(requestresult, Client.class);
        return result;
    }

    public static GetAll RequestAllClients() throws IOException{
        String requestresult = httpGet(ip + "/api/contextaware/v1/location/clients/", "admin", "admin");
        Gson gson = new GsonBuilder().create();
        GetAll result = gson.fromJson(requestresult, GetAll.class);
        return result;
    }
}