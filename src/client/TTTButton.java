package client;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TTTButton extends Button{

	private String value;
	private static int button_count = 0;
	
	public TTTButton() {
		super();
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
}
