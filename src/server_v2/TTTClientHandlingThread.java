package server_v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import common_v2.TTTMsg;
import common_v2.TTTPlayer;

public class TTTClientHandlingThread extends Thread {

	private static int clientNumber = 0;
	private final Logger logger = Logger.getLogger("");
	private Socket socket;
	private TTTPlayer player;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private TTTServerModel model;

	@Deprecated
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
	
	public TTTClientHandlingThread(Socket socket, TTTServerModel model) {
		super("Client thread" + clientNumber++);
		this.socket = socket;
		this.model = model;
		
		try {
			out = new ObjectOutputStream(this.socket.getOutputStream());
			in = new ObjectInputStream(this.socket.getInputStream());
			
			this.player = new TTTPlayer(in, out);
			
			out.writeObject(this.player);
			this.start();
//			TTTMsg msg = (TTTMsg) in.readObject();
//			
//			while(msg != null) {
//				System.out.println("Looping");
//				System.out.println("Hi object " + msg.getButton().getButtonValue() + " ID: " + msg.getButtonID());
//				TTTServerModel.writeToOutput("Hi object " + msg.getButton().getButtonValue() + " ID: " + msg.getButtonID());
//				msg = (TTTMsg) in.readObject();
//				
//				//send this msg to server so it can send it to the other client
//				while(model.threadToSocket.values().iterator().hasNext()) {
//					if(model.threadToSocket.values().iterator().next() == this.socket) {
//						System.out.println("Do not send to myself");
//					}else if(model.threadToSocket.values().iterator().next() != this.socket) {
//						System.out.println("Send to peer");
//						ObjectOutputStream oos = new ObjectOutputStream(model.threadToSocket.values().iterator().next().getOutputStream());
//						oos.writeObject(msg);
//					}else {
//						System.out.println("not sending any information, no other player found");
//					}
//				}
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	public void run() {
		
		TTTMsg msg;
		try {
			msg = (TTTMsg) in.readObject();
			while(msg != null) {
				System.out.println("Looping");
				System.out.println("Hi object " + msg.getButton().getButtonValue() + " ID: " + msg.getButtonID());
				TTTServerModel.writeToOutput("Hi object " + msg.getButton().getButtonValue() + " ID: " + msg.getButtonID());
				msg = (TTTMsg) in.readObject();
				
				//send this msg to server so it can send it to the other client
				while(model.threadToSocket.values().iterator().hasNext()) {
					if(model.threadToSocket.values().iterator().next() == this.socket) {
						System.out.println("Do not send to myself");
					}else if(model.threadToSocket.values().iterator().next() != this.socket) {
						System.out.println("Send to peer");
						ObjectOutputStream oos = new ObjectOutputStream(model.threadToSocket.values().iterator().next().getOutputStream());
						oos.writeObject(msg);
					}else {
						System.out.println("not sending any information, no other player found");
					}
				}
			}
			msg = null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
