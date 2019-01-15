package client_v2;

import java.util.logging.Logger;


public class TTTControl {
	private TTTModel model;
	private TTTView view;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	public TTTControl(TTTModel m, TTTView v) {
		this.model = m;
		this.view = v;
		
		v.getPlayerButtons().forEach(b ->{
			b.setOnAction(e ->{
				if(model.isGameStarted()) {
					model.action(b);
					b.setButtonValue(v.getPlayerCode());;
				}
			});
		});
		
//		v.getPlayerButtons().forEach(b -> {
//			b.setOnAction(e ->{
//				if(model.isGameStarted()) {
//					logger.fine("Game started");
//					b.takeMe(v.getPlayerCode());
//					model.send(b.getButtonID());
//					model.setCurrentSum(b.getSummaricValue());
//				}
//			});
//		});
//		
		v.startGame.setOnAction(e ->{
			if(v.getPlayerCode().equals("")) {
				
			}else {
				model.startGame();
				model.setPlayerChar(v.getPlayerCode());
				v.charTypeInput.setDisable(true);
			}
		});
	}
}
