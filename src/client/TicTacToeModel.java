package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class TicTacToeModel{

	private boolean gameStarted = false;
	private String playerChar = "";
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	private Socket s = null;
	private InputStream in;
	private InputStreamReader inr;
	private OutputStreamWriter out = null;
    private BufferedReader inReader = null;
	
	public TicTacToeModel() {
		
	}
	
	public void startGame() {
		this.gameStarted = true;
	}
	public boolean isGameStarted() {
		return this.gameStarted;
	}
	
	public void setPlayerChar(String s) {
		this.playerChar = s;
	}
	
	/*function of my socket: 
	 * tell server which button was clicked by user
	 * disable GUI until response comes
	 * */
	
	public void send(int buttonNr) {
		try {
			this.s = new Socket("127.0.0.1", 8088);
			
			out = new OutputStreamWriter(s.getOutputStream());
			logger.info(playerChar + ":LOGGER:" + buttonNr);
			out.write(playerChar + ":" + buttonNr);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receive(String otherPlayerChar, int otherPlayerButtonNr) {
		try {
			in = s.getInputStream();
			inr = new InputStreamReader(in);
			inReader = new BufferedReader(inr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
