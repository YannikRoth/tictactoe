package common_v2;

import java.io.Serializable;

public class TTTMsg implements Serializable{

	private int buttonID;
	private TTTButton button;
	private TTTPlayer player;
	
	public TTTMsg(TTTButton b, TTTPlayer p) {
		this.buttonID = b.getButtonID();
		this.button = b;
		this.player = p;
	}
	
	public int getButtonID() {
		return buttonID;
	}

	public void setButtonID(int buttonID) {
		this.buttonID = buttonID;
	}

	public TTTButton getButton() {
		return button;
	}

	public void setButton(TTTButton button) {
		this.button = button;
	}

	public TTTPlayer getPlayer() {
		return player;
	}

	public void setPlayer(TTTPlayer player) {
		this.player = player;
	}

	
	
}
