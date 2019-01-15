package server_v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import common_v2.Config;
import common_v2.TTTPlayer;
import javafx.beans.property.SimpleStringProperty;

public class TTTServerModel extends Thread{
	
	static SimpleStringProperty outputField = new SimpleStringProperty();
	
	private volatile ArrayList<Socket> sockets = new ArrayList<Socket>();
	private volatile ArrayList<TTTClientHandlingThread> client_threads = new ArrayList<TTTClientHandlingThread>();
	private Map<TTTPlayer, Socket> matchedPlayers = new HashMap<TTTPlayer, Socket>();
	protected volatile Map<TTTClientHandlingThread, Socket> threadToSocket = new HashMap<TTTClientHandlingThread, Socket>();
	
	public TTTServerModel() {
		
	}
	
	@Override
	public void run() {
		writeToOutput("Game started on port " + Config.PORT);
		System.out.println("Game started on port " + Config.PORT);
		
		try {
			System.out.println("Creating server socket");
			ServerSocket ss = new ServerSocket(Config.PORT, 10, null);
			System.out.println("Created server socket");
			while(true) {
				System.out.println("Entering loop");
				Socket s = ss.accept();
				writeToOutput(s.toString());
				TTTClientHandlingThread t = new TTTClientHandlingThread(s, this);
				sockets.add(s);
				client_threads.add(t);
				threadToSocket.put(t, s);
				t.start();
				writeToOutput("Thread started.");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static void writeToOutput(String s) {
		String current = outputField.getValue();
		if(current == null) {
			current = "";
		}
		String new_text = Calendar.getInstance().getTime() + ": ---> " + s + "\n" + current;
		outputField.setValue(new_text);
	}

}
