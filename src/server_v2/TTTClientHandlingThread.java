package server_v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import common_v2.TTTPlayer;

public class TTTClientHandlingThread extends Thread {

	private static int clientNumber = 0;
	private final Logger logger = Logger.getLogger("");
	private Socket socket;
	private TTTPlayer player;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public TTTClientHandlingThread(Socket socket, TTTPlayer player) {
		super("Client thread" + clientNumber++);
		this.player = player;
		this.socket = socket;
		
		try {
			out = new ObjectOutputStream(this.socket.getOutputStream());
			in = new ObjectInputStream(this.socket.getInputStream());
			
			out.writeObject(this.player);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {

	}
}
