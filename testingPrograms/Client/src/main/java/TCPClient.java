import java.io.*;
import java.net.*;
class TCPClient {
    public static void main(String argv[]) throws Exception
    {
        String sentence;
        String modifiedSentence;

        Socket clientSocket = new Socket("172.26.120.105", 8080);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        for(int i = 0; i < 500;i++){
            String inFromUser = "Test " + i;
            sentence = inFromUser;
            outToServer.writeBytes(sentence + '\n');
 //           modifiedSentence = inFromServer.readLine();
            Thread.sleep(100);
            System.out.println(i);
        }
//        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
    }
}