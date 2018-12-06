package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class TicTacToeMVC extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	public void start(Stage primaryStage) {
		//setting up GUI
		TicTacToeModel model = new TicTacToeModel();
		TicTacToeView view = new TicTacToeView(primaryStage, model);
		TicTacToeController controller = new TicTacToeController(model, view);
	}

}
