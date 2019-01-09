package common_v2;

import javafx.scene.control.Button;

public class TTTButton extends Button{
	
	private String value;
	private int buttonID = 0;
	private int buttonSummaricValue = -1;
	private static int button_count = 0;
	
	public TTTButton() {
		super();
		this.buttonID = button_count;
		this.buttonSummaricValue = (int) Math.pow(2, button_count);
		button_count++;
		this.setMaxSize(500, 500);
		this.setMinSize(80, 80);
		this.setStyle("-fx-font-size: 20pt");
		
	}
	
	public void takeMe(String p) {
		this.value = p;
		this.setText(p);
	}
	
	public static int getButtonCount() {
		return button_count;
	}
	public int getButtonID() {
		return this.buttonID;
	}
	public int getSummaricValue() {
		return this.buttonSummaricValue;
	}
}
