package client_v2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common_v2.Config;
import common_v2.TTTButton;
import common_v2.TTTMsg;
import common_v2.TTTPlayer;

public class TTTModel {
	
	private boolean started;
	private boolean myTurn;
	private String playedCode;
	private Socket socket;
	private TTTPlayer player;
	private FileInputStream fileInputStream;
	private ObjectOutputStream objOutputStream;
	private ObjectInputStream objInputStream;
	
	public TTTModel() {
		//
	}
	
	public boolean isGameStarted() {
		return this.started;
	}
	
	public void startGame() {
		this.started = true;
		
		//set up server connection
		setUpConnection();
	}
	
	public void changePlayerTurn() {
		if(this.myTurn) {
			this.myTurn = false;
		}else {
			this.myTurn = true;
		}
	}
	
	public void setPlayerChar(String s) {
		this.playedCode = s;
	}
	
	public void action(TTTButton button) {				
		TTTMsg msg = new TTTMsg(button, player);
		button.setText(this.playedCode);
		button.setDisable(true);
		System.out.println(button.getButtonID());
		
		//send to server
		sendButtonToServer(msg);
		
	}
	
	private void setUpConnection() {
		try {
			socket = new Socket(Config.IP_ADD, Config.PORT);
			System.out.println("hi");
			objOutputStream = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("hi");
			objInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("hi");
			player = new TTTPlayer(socket, objInputStream, objOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendButtonToServer(TTTMsg msg) {
		
	}
	
	

}
