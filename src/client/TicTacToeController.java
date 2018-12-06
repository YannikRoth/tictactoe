package client;

public class TicTacToeController {
	
	private TicTacToeModel model;
	private TicTacToeView view;
	
	public TicTacToeController(TicTacToeModel m, TicTacToeView v) {
		this.model = m;
		this.view = v;
		
		v.getPlayerButtons().forEach(b -> {
			b.setOnAction(e ->{
				/* TODO: 
				 * X represents the players selection
				 * this should however be changed dynamically
				 */
				b.takeMe(v.getPlayerCode());
			});
		});
		
		v.startGame.setOnAction(e ->{
			v.charTypeInput.setDisable(true);
		});
	}

}
