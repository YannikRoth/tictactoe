package server_v2;

import javafx.application.Application;
import javafx.stage.Stage;

public class TTTServerMVC extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		TTTServerModel model = new TTTServerModel();
		TTTServerView view = new TTTServerView(primaryStage, model);
		TTTServerControl control = new TTTServerControl(model, view);
		
	}
	

}
