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
	
	SimpleStringProperty outputField = new SimpleStringProperty();
	ArrayList<TTTPlayer> players = new ArrayList<TTTPlayer>();
	ArrayList<Socket> sockets = new ArrayList<Socket>();
	HashMap<TTTPlayer, Socket> matchedPlayers = new HashMap<TTTPlayer, Socket>();
	ArrayList<TTTClientHandlingThread> client_threads = new ArrayList<TTTClientHandlingThread>();
	
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
				TTTPlayer p = new TTTPlayer(s);
				matchedPlayers.put(p, s);
				TTTClientHandlingThread t = new TTTClientHandlingThread(s, p);
				client_threads.add(t);
				t.start();
				writeToOutput("Thread started.");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	private void writeToOutput(String s) {
		String current = outputField.getValue();
		if(current == null) {
			current = "";
		}
		String new_text = Calendar.getInstance().getTime() + ": ---> " + s + "\n" + current;
		outputField.setValue(new_text);
	}

}
