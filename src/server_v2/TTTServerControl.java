package server_v2;

public class TTTServerControl {

	private TTTServerModel model;
	protected TTTServerView view;
	
	TTTServerControl(TTTServerModel m, TTTServerView v){
		this.model = m;
		this.view = v;
		
		view.startGame.setOnAction(e ->{
			model.start();
		});
	}
}
