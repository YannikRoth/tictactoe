package common_v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TTTPlayer {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private static int player_count = 1;
	private int player_id;
	
	public TTTPlayer(Socket s, ObjectInputStream in, ObjectOutputStream out) {
		socket = s;
		this.in = in;
		this.out = out;
		
		this.player_id = player_count;
		player_count++;
	}
	
	public TTTPlayer(Socket s) {
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	
	public int getPlayerID() {
		return this.player_id;
	}
	public ObjectInputStream getObjInStream() {
		return this.in;
	}
}
