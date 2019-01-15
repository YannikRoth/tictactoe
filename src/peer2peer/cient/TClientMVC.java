package peer2peer.cient;

import javafx.application.Application;
import javafx.stage.Stage;

public class TClientMVC extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TClientModel model = new TClientModel();
		TClientView view = new TClientView(primaryStage, model);
		TClientControl control = new TClientControl(view, model);
		
	}
}
