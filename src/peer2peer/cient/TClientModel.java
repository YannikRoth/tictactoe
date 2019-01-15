package peer2peer.cient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;

import common_v2.TTTButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import peer2peer.Config;
import peer2peer.TMsg;
import peer2peer.host.THostView;

public class TClientModel {
	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String playerChar;
	private ArrayList<Integer> playedButtons = new ArrayList<Integer>();
	
	public TClientModel() {
		try {
			socket = new Socket(Config.IP_ADDR, Config.PORT);
			System.out.println("Connection established");
			
			System.out.println("Setting up output stream");
			oos = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Setting up input stream");
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println("Finished");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendButton(int buttonID) {
//		TClientView.getButtons().forEach(b ->{
//			if(b.getButtonID()== buttonID) {
//				System.out.println("Looping");
//				b.setText(playerChar);
//				b.setDisable(true);
//			}
//		});
		
		TMsg msg = new TMsg(this.playerChar, buttonID);
		playedButtons.add(buttonID);
		evaluate();
		try {
			oos.writeObject(msg);
			oos.flush();
			System.out.println("Client: Msg sent");
			System.out.println("Client: Waiting for msg");
			receiveButton();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void receiveButton() {
		try {
			TMsg msg = (TMsg) ois.readObject();
			System.out.println("Client: Msg received");
			updateGUI(msg);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateGUI(TMsg msg) {
		int i = msg.getButtonID();
		System.out.println("i is equal to: " + i);
		String s = msg.getPlayerChar();
		
		TClientView.getButtons().forEach(b ->{
			//System.out.println("button id is: " + b.getButtonID());
			if(b.getButtonID() == i) {
				b.setText(s);
				b.setDisable(true);
			}
		});
	}
	
	public void setPlayerChar(String text) {
		this.playerChar = text;
		
	}
	
	public void evaluate() {
		playedButtons.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
			
		});
		
		playedButtons.forEach(i ->{
			System.out.println("Sorted" + i);
		});
		
		//int smallest = playedButtons.get(0);
		boolean win = false;
		if(playedButtons.indexOf(0) != -1 && playedButtons.indexOf(1) != -1 && playedButtons.indexOf(2) != -1) {win = true; changeColor(0,1,2);};
		if(playedButtons.indexOf(3) != -1 && playedButtons.indexOf(4) != -1 && playedButtons.indexOf(5) != -1) {win = true; changeColor(3,4,5);};
		if(playedButtons.indexOf(6) != -1 && playedButtons.indexOf(7) != -1 && playedButtons.indexOf(8) != -1) {win = true; changeColor(6,7,8);};
		if(playedButtons.indexOf(0) != -1 && playedButtons.indexOf(3) != -1 && playedButtons.indexOf(6) != -1) {win = true; changeColor(0,3,6);};
		if(playedButtons.indexOf(1) != -1 && playedButtons.indexOf(4) != -1 && playedButtons.indexOf(7) != -1) {win = true; changeColor(1,4,7);};
		if(playedButtons.indexOf(2) != -1 && playedButtons.indexOf(5) != -1 && playedButtons.indexOf(8) != -1) {win = true; changeColor(2,5,8);};
		if(playedButtons.indexOf(0) != -1 && playedButtons.indexOf(4) != -1 && playedButtons.indexOf(8) != -1) {win = true; changeColor(0,4,8);};
		if(playedButtons.indexOf(2) != -1 && playedButtons.indexOf(4) != -1 && playedButtons.indexOf(6) != -1) {win = true; changeColor(2,4,6);};
		
		if(win) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("WIN");
			alert.setContentText("You won the game!");

			alert.showAndWait();
		}
		
		
	}
	
	private void changeColor(int ...buttonIds) {
		for(int i : buttonIds) {
			TClientView.getButtons().forEach(b ->{
				if(b.getButtonID() == i) {
					b.setStyle("-fx-background-color: #ff0000; ");
				}
			});
		}
	}

}
