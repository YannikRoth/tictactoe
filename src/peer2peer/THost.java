package peer2peer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class THost {
	
	protected ServerSocket ss;
	protected Socket s;
	
	public THost() {
		try {
			ss = new ServerSocket(Config.PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startGame() {
		
	}

}
