package client_v2;

import javafx.application.Application;
import javafx.stage.Stage;

public class TTTMVC extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	public void start(Stage primaryStage) {
		//setting up GUI
		TTTModel model = new TTTModel();
		TTTView view = new TTTView(primaryStage, model);
		TTTControl controller = new TTTControl(model, view);
	}

}
