import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class chatServer {
	static Vector clientSockets;
	static Vector loginNames;
	
	ChatServer() throws IOException
	{
		ServerSocket server = new ServerSocket(1738);
		clientSockets = new Vector();
		loginNames = new Vector();
		
		while(true)
		{
			Socket client = server.accept();
			AcceptClient acceptClient = new AcceptClient(client);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		chatServer server = new chatServer();
	}
	
	class AcceptClient extends Thread
	{
		Socket clientSocket;
		DataInputStream dataIn;
		DataOutputStream dataOut;
		AcceptClient(Socket client) throws IOException
		{
			clientSocket = client;
			dataIn = new DataInputStream(clientSocket.getInputStream());
			dataOut = new DataOutputStream(clientSocket.getOutputStream());
			
			String loginName = dataOut.readUTF();
			
			loginNames.add(loginNames);
			clientSockets.add(clientSocket);
			
			start();
		}
		
		public void run()
		{
			while(true)
			{
				try{
					String msgFromClient = dataIn.readUTF();
					StringTokenizer stringToken = new StringTokenizer(msgFromClient);
					String LoginName = stringToken.nextToken();
					String msgType = stringToken.nextToken();
					
					for (int i = 0; i < loginNames.size(); i++) {
						Socket pSocket = (Socket) clientSockets.elementAt(i);
						DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
						pOut.writeUTF(loginName + " has logged in.");
						
					}
				} catch (IOException e)
				
			}
		}
	}

}
