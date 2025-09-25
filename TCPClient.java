import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

class TCPClient {
	public static void main(String argv[]) throws Exception
    {
        String sentence = "";
        String modifiedSentence = "";

        System.out.println("This is client side!!!");
		System.out.println("----------------------");
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println("START TIME: " + formatter.format(date));
		
        // Create input stream
        BufferedReader inFromUser =
          new BufferedReader(new InputStreamReader(System.in));
        
        // Create client socket, connect to server at port 6789
        Socket clientSocket = new Socket("localhost", 6789);
        
        // Create output stream attached to socket
        DataOutputStream outToServer =
          new DataOutputStream(clientSocket.getOutputStream());

        // Create input stream attached to socket
        BufferedReader inFromServer =
          new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        while(!sentence.equals("Over"))
        {
	        try {    
	        	
	            // Read line from client input
	            sentence = inFromUser.readLine();
	            
		        // Send line to server
		        outToServer.writeBytes(sentence + '\n');
		        System.out.println("SEND TO SERVER: " + sentence);
		
		        // Read line from server
		        modifiedSentence = inFromServer.readLine();
		        
		        System.out.println("TEXT MESSAGE RECEIVED FROM SERVER: " + modifiedSentence);
	        }
			catch(IOException i)
			{
				System.out.println(i);
			}
        }
        
		// close the connection
		try
		{
			outToServer.close();
			inFromUser.close();
	
	        // Close client socket 
	        clientSocket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
                  
    }
}