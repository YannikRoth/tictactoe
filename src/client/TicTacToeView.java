package client;

import javafx.stage.Stage;

public class TicTacToeView {

	private TicTacToeModel model;
	private Stage stage;
	
	public TicTacToeView(Stage s, TicTacToeModel m) {
		this.model = m;
		this.stage = s;
	}
}
