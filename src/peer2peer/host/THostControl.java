package peer2peer.host;

import javafx.application.Platform;

public class THostControl {
	
	private THostView view;
	private THostModel model;
	
	public THostControl(THostView view, THostModel model) {
		this.view =view;
		this.model = model;
		
		
		view.startGame.setOnAction(e ->{
			model.setPlayerChar(view.charTypeInput.getText());
			model.setUpConnection();
		});
		
		view.getButtons().forEach(b ->{
			b.setOnAction(e ->{
				System.out.println("Clicked" + b.getButtonID());
				Thread t = new UpdateGUIHost(view, b.getButtonID(), view.charTypeInput.getText());
				Platform.runLater(t);
				model.sendButton(b.getButtonID());
			});
		});
	}

}
