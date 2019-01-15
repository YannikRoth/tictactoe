package peer2peer;

import java.io.Serializable;

public class TMsg implements Serializable{

	private String playerChar ="";
	private int buttonID;
	
	public TMsg(String s, int i) {
		this.playerChar = s;
		this.buttonID = i;
	}
	
	public String getPlayerChar() {
		return playerChar;
	}

	public void setPlayerChar(String playerChar) {
		this.playerChar = playerChar;
	}

	public int getButtonID() {
		return buttonID;
	}

	public void setButtonID(int buttonID) {
		this.buttonID = buttonID;
	}
	
}
