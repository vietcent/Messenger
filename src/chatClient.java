import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class chatClient extends JFrame implements Runnable {
	
	Socket socket;
	JTextArea textArea;
	Thread thread;
	DataInputStream dataIn;
	DataOutputStream dataOut;
	String loginName;
	
	chatClient(String login){
		super(login);
		loginName = login;
		
		textArea = new JTextArea(18,50);
		socket = new Socket("localhost", 1738);
		
		dataIn = new DataInputStream(socket.getInputStream());
		dataOut = new DataOutputStream(socket.getOutputStream());
		
		dataOut.writeUTF(loginName);
		dataOut.writeUTF(loginName + " " + "LOGIN");
		
		thread = new Thread(this);
		thread.start();
		setup();
		
	}
	
	private void setup()
	{
		setSize(600, 400);
		JPanel panel = new JPanel();
		panel.add(new JScrollPane(textArea));
		add(panel);
		setVisible(true);
		
	}
	
	public void run()
	{
		while (true) {
			try {
				textArea.append("\n" + dataIn.readUTF());
				
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		chatClient client = new chatClient("User1");
	}

}
