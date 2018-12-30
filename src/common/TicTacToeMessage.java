package common;

import java.io.Serializable;

public class TicTacToeMessage implements Serializable {
	
	private Integer btnNumber = null;
	private String playerChar = null;
	
	public TicTacToeMessage(int i, String s) {
		this.btnNumber = i;
		this.playerChar = s;
	}
	
	public void setBtnNumber(int i) {
		this.btnNumber = i;
	}
	
	public void setPlayerChar(String s) {
		this.playerChar = s;
	}
}
