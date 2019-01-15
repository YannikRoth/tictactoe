package peer2peer.host;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;

import com.sun.prism.paint.Color;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import peer2peer.Config;
import peer2peer.TMsg;
import peer2peer.cient.TClientView;

public class THostModel {

	private ServerSocket ss;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String playerChar;
	private ArrayList<Integer> playedButtons = new ArrayList<Integer>();
	
	public THostModel() {
		try {
			ss = new ServerSocket(Config.PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setUpConnection() {
		try {
			System.out.println("Waiting for connection");
			socket = this.ss.accept();
			System.out.println("Connection found");
			
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
		playedButtons.add(buttonID);
		evaluate();
		TMsg msg = new TMsg(this.playerChar, buttonID);
//		THostView.getButtons().forEach(b ->{
//			if(b.getButtonID()== buttonID) {
//				b.setText(playerChar);
//				b.setDisable(true);
//			}
//		});
		
		try {
			oos.writeObject(msg);
			oos.flush();
			System.out.println("Host: Msg sent");
			System.out.println("Host: Waiting for msg");
			receiveButton();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receiveButton() {
		try {
			TMsg msg = (TMsg) ois.readObject();
			System.out.println("Host: Msg received");
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
		
		THostView.getButtons().forEach(b ->{
			if(b.getButtonID() == i) {
				b.setText(s);
				b.setDisable(true);
			}
		});
	}

	public void setPlayerChar(String s) {
		this.playerChar = s;
		
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
		
		int smallest = playedButtons.get(0);
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
			THostView.getButtons().forEach(b ->{
				if(b.getButtonID() == i) {
					b.setStyle("-fx-background-color: #ff0000; ");
				}
			});
		}
	}

	public String getPlayerChar() {
		return this.playerChar;
	}

}
