import java.io.*;
import java.net.*;
import java.util.Base64;

class TCPClient {
    public static void main(String argv[]) throws Exception
    {
        String sentence;
        String modifiedSentence;


        String inFromUser;
        //for(int i = 0; i < 2;i++) {
            /*
            Socket clientSocket = new Socket("10.0.0.2", 8080);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromUser = "Test " + i;
            sentence = inFromUser;
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            Thread.sleep(10);
            System.out.println(modifiedSentence);
            clientSocket.close();
                */

        System.out.println("FROM SERVER: " + httpGet("http://172.26.120.105:8080/online", "test", "works"));
        System.out.println("FROM SERVER: " + httpGet("http://172.26.120.105:8080/api/contextaware/v1/location/clients/00:00:2a:01:00:0a", "test1", "worksk"));
        System.out.println("FROM SERVER: " + httpGet("http://172.26.120.105:8080/api/contextaware/v1/location/clients", "test", "works"));

        System.out.println("FROM SERVER: " + httpGet("http://172.26.120.105:8080/api/contextaware/v1/location/clients/00:00:2a:01:00:0a", "test", "works"));
        System.out.println("FROM SERVER: " + httpGet("http://172.26.120.105:8080/api/watchlist/add/00:00:2a:01:00:0a", "test", "works"));
        System.out.println("FROM SERVER: " + httpGet("http://172.26.120.105:8080/api/contextaware/v1/location/clients/00:00:2a:01:00:0a", "test", "works"));
        System.out.println("FROM SERVER: " + httpGet("http://172.26.120.105:8080/api/watchlist/remove/00:00:2a:01:00:0a", "test", "works"));
        System.out.println("FROM SERVER: " + httpGet("http://172.26.120.105:8080/api/contextaware/v1/location/clients/00:00:2a:01:00:0a", "test", "works"));
    }
    private static String Authentication(String name, String password) {
        String temp = name + ":" + password;
        Base64.Encoder enc = Base64.getEncoder();
        String temp2 = enc.encodeToString(temp.getBytes());
        return "Basic " + temp2;
    }

    private static String httpGet(String urlStr, String userName, String userPW) throws java.io.IOException {
        URL url = new URL(urlStr);
        //String ip = getIP();
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();
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
}