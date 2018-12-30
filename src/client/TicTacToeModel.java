package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import common.TicTacToeMessage;

public class TicTacToeModel{

	private boolean gameStarted = false;
	private String playerChar = "";
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	private Socket s = null;
	private InputStream in;
	private ObjectInputStream obj_in;
	private InputStreamReader inr;
	private OutputStreamWriter out = null;
	private ObjectOutputStream obj_out = null;
    private BufferedReader inReader = null;
    
    private int[] winningSums = {7,56,73,84,146,273,292,448};
    private int currentSum = 0;
	
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
			obj_out = new ObjectOutputStream(s.getOutputStream());
			logger.info(playerChar + ":LOGGER:" + buttonNr);
			out.write(playerChar + ":" + buttonNr);
			
			TicTacToeMessage tttm = new TicTacToeMessage(buttonNr, playerChar);
			obj_out.writeObject(tttm);
			
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
			obj_in = new ObjectInputStream(in);
			inr = new InputStreamReader(in);
			inReader = new BufferedReader(inr);
			
			TicTacToeMessage tttm = (TicTacToeMessage) obj_in.readObject();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setCurrentSum(int i) {
		this.currentSum += i;
		System.out.println(currentSum);
	}
	public void resetCurrentSum() {
		this.currentSum = 0;
	}
	public boolean evaluateWin() {
		return false;
	}

}
