package server_v2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TTTServerView {

	private Stage stage;
	private TTTServerModel model;
	
	//GUI Elements
	protected BorderPane root;
	protected HBox menu;
	protected TextField input;
	protected TextArea output;
	protected Button startGame;
	protected Label port;
	
	public TTTServerView(Stage s, TTTServerModel m) {
		this.stage = s;
		this.model = m;
		
		this.buildGUI();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void buildGUI() {
		root = new BorderPane();
		menu = new HBox();
		input = new TextField();
		output = new TextArea();
		startGame = new Button("Start game session");
		port = new Label("Port: ");
		
		menu.getChildren().addAll(port, input, startGame);
		root.setTop(menu);
		root.setCenter(output);
		
		output.textProperty().bind(model.outputField);
		
	}
	
}
