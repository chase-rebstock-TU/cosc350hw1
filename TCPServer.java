// Chase Rebstock
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        // Create welcoming socket at port 12211
        ServerSocket welcomeSocket = new ServerSocket(12211);

        System.out.println("This is server side!!!");
        System.out.println("----------------------");

        // Wait for incoming connection request
        Socket connectionSocket = welcomeSocket.accept();

        // Input/output streams
        BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        // Receive two strings
        String string1 = inFromClient.readLine();
        String string2 = inFromClient.readLine();

        System.out.println("STRINGS RECEIVED: " + string1 + ", " + string2);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

        // ---- Handle W1 ----
        Date st1 = new Date();
        System.out.println("W1 REQUEST SENT AT " + formatter.format(st1));

        Call_HttpURLConnection(string1, "W1.txt");

        Date st2 = new Date();
        System.out.println("W1 RESPONSE RECEIVED AT " + formatter.format(st2));
        long t1 = st2.getTime() - st1.getTime();
        System.out.println("W1 DELAY: " + t1 + " ms");

        // ---- Handle W2 ----
        Date st3 = new Date();
        System.out.println("W2 REQUEST SENT AT " + formatter.format(st3));

        Call_HttpURLConnection(string2, "W2.txt");

        Date st4 = new Date();
        System.out.println("W2 RESPONSE RECEIVED AT " + formatter.format(st4));
        long t2 = st4.getTime() - st3.getTime();
        System.out.println("W2 DELAY: " + t2 + " ms");

        // Send back a message to client
        String reply = "Processed domains: " + string1 + " and " + string2;
        outToClient.writeBytes(reply + "\n");

        // Close resources
        outToClient.close();
        inFromClient.close();
        connectionSocket.close();
        welcomeSocket.close();
    }

    public static void Call_HttpURLConnection(String url_str, String filename) throws Exception {
        // Ensure HTTPS connection on port 443
        URL url = new URL("https://" + url_str);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        FileWriter myWriter = new FileWriter(filename, StandardCharsets.UTF_8);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            myWriter.write(inputLine + "\n");
        }

        myWriter.close();
        in.close();
        System.out.println("Successfully wrote to the file " + filename + ".");
    }
}
