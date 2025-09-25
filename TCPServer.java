import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class TCPServer {

	public static void main(String argv[]) throws Exception
    {
		String clientSentence = "start";
		String capitalizedSentence = "";
	  
		// Create welcoming socket at port 6789
		ServerSocket welcomeSocket = new ServerSocket(6789);
	  
		System.out.println("This is server side!!!");
		System.out.println("----------------------");
	  
		// Wait for incoming connection request
		Socket connectionSocket = welcomeSocket.accept();
	
		// Create input stream, attached to socket
		BufferedReader inFromClient =
				new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	
		// Create output stream, attached to socket
		DataOutputStream outToClient =
				new DataOutputStream(connectionSocket.getOutputStream());
		
        
		while (!clientSentence.substring(0, 4).equals("Over"))
		{  
			// Read in line from socket
			clientSentence = inFromClient.readLine();
			System.out.println("STRINGS RECEIVED FROM CLIENT: " + clientSentence);
		      
			// Get the capitalized sentence
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
		
			// Write out line to socket
			outToClient.writeBytes(capitalizedSentence);
		} 
		
		System.out.println("Closing connection...");
		
		// close connection
		welcomeSocket.close();
		outToClient.close();
		
    }
  
	public static void Call_HttpURLConnection(String url_str, String filename) throws Exception
	{
		String inputLine;
		int PORT = 443;
		
		// Get URL string
		URL url = new URL("https://" + url_str);
		
		// Assign port number
		URL newurl = new URL(url.getProtocol(), url.getHost(), PORT, url.getFile());
		
		HttpURLConnection con = (HttpURLConnection) newurl.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent","Mozilla/5.0");
		BufferedReader in = new BufferedReader(
		     new InputStreamReader(con.getInputStream())); 
		
		FileWriter myWriter = new FileWriter(filename, StandardCharsets.UTF_8);
		while ((inputLine = in.readLine()) != null) 
		{
			myWriter.write(inputLine);  
		}
		
		myWriter.close();
	    System.out.println("Successfully wrote to the file " + filename + ".");
			     
	}
}