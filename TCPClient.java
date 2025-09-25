// Chase Rebstock
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        // Print START TIME
        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("START TIME: " + formatter.format(date1));

        // Input from user
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Connect to local server at port 12211
        Socket clientSocket = new Socket("localhost", 12211);

        // Output stream to server
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // Input stream from server
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Prompt for string 1
        System.out.println("ENTER STRING 1:");
        String string1 = inFromUser.readLine();

        // Prompt for string 2
        System.out.println("ENTER STRING 2:");
        String string2 = inFromUser.readLine();

        // Send both strings to server
        outToServer.writeBytes(string1 + "\n");
        outToServer.writeBytes(string2 + "\n");

        // Receive message from server
        String response = inFromServer.readLine();
        System.out.println("MESSAGE FROM SERVER: " + response);

        // Print END TIME
        Date date2 = new Date();
        System.out.println("END TIME: " + formatter.format(date2));

        // Close resources
        outToServer.close();
        inFromServer.close();
        inFromUser.close();
        clientSocket.close();
    }
}
