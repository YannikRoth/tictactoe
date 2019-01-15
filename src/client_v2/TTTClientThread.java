package client_v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common_v2.TTTMsg;
import server_v2.TTTServerModel;

public class TTTClientThread extends Thread{

	private Socket socket;
	private TTTModel model;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public TTTClientThread(Socket socket, TTTModel model, ObjectOutputStream out, ObjectInputStream in) {
		super();
		this.socket = socket;
		this.model = model;
	}
	
	@Override
	public void run() {
		try {
			//out = new ObjectOutputStream(this.socket.getOutputStream());
			this.out = out;
			//in = new ObjectInputStream(this.socket.getInputStream());
			this.in = in;
			
			TTTMsg msg = (TTTMsg) in.readObject();
			
			while(msg != null) {
				System.out.println("Looping");
				System.out.println("Hi object " + msg.getButton().getButtonValue() + " ID: " + msg.getButtonID());
				TTTServerModel.writeToOutput("Hi object " + msg.getButton().getButtonValue() + " ID: " + msg.getButtonID());
				msg = (TTTMsg) in.readObject();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
