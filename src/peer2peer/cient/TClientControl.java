package peer2peer.cient;

import javafx.application.Platform;

public class TClientControl {
	
	private TClientView view;
	private TClientModel model;
	
	public TClientControl(TClientView view, TClientModel model) {
		this.view = view;
		this.model = model;
		
		view.getButtons().forEach(b ->{
			b.setOnAction(e ->{
				System.out.println("Clicked" + b.getButtonID());
				Thread t = new UpdateGUI(view, b.getButtonID(), view.charTypeInput.getText());
				Platform.runLater(t);
				model.sendButton(b.getButtonID());
			});
		});
		
		view.startGame.setOnAction(e ->{
			model.setPlayerChar(view.charTypeInput.getText());
			model.receiveButton();
		});
	
	}

}
