package client;

public class TicTacToeController {
	
	private TicTacToeModel model;
	private TicTacToeView view;
	
	public TicTacToeController(TicTacToeModel m, TicTacToeView v) {
		this.model = m;
		this.view = v;
	}

}
