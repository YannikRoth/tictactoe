package client;

import java.util.logging.Logger;

public class TicTacToeController {
	
	private TicTacToeModel model;
	private TicTacToeView view;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	public TicTacToeController(TicTacToeModel m, TicTacToeView v) {
		this.model = m;
		this.view = v;
		
		v.getPlayerButtons().forEach(b -> {
			b.setOnAction(e ->{
				if(model.isGameStarted()) {
					logger.fine("Game started");
					b.takeMe(v.getPlayerCode());
					model.send(b.getButtonID());
				}
			});
		});
		
		v.startGame.setOnAction(e ->{
			model.startGame();
			model.setPlayerChar(v.getPlayerCode());
			v.charTypeInput.setDisable(true);
		});
	}

}
