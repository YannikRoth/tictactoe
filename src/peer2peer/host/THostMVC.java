package peer2peer.host;

import javafx.application.Application;
import javafx.stage.Stage;

public class THostMVC extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		THostModel model = new THostModel();
		THostView view = new THostView(primaryStage, model);
		THostControl control = new THostControl(view, model);
		
	}

}
