package peer2peer.host;

import java.util.ArrayList;
import java.util.logging.Logger;

import common_v2.TTTButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import peer2peer.cient.TClientModel;

public class THostView {
	

	private THostModel model;
	private Stage stage;
	private BorderPane root;
	private GridPane playField;
	private HBox manageGame;
	private Label charType;
	protected TextField charTypeInput;
	protected Button startGame;
	private static ArrayList<TTTButton> buttons;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	public THostView(Stage s, THostModel m) {
		
		this.model = m;
		this.stage = s;
		s.setTitle("HOST");
		
		root = new BorderPane();
				
		initializeGUI();
		root.setCenter(playField);
		root.setBottom(manageGame);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void initializeGUI() {
		playField = new GridPane();
		playField.setGridLinesVisible(true);
		playField.setHgap(20);
		playField.setVgap(20);
		buttons = new ArrayList<TTTButton>();
		
		//create buttons
		for(int i=0; i<9; i++) {
			buttons.add(new TTTButton());
		}
		//add buttons to GridPane
		int col = 0;
		int row = 0;
		for(int i=0; i<9; i++) {
			playField.add(buttons.get(i), col, row);
			if((col+1) % 3 == 0) {
				//new row
				logger.info("New row: " + row);
				col = 0;
				row++;
			}else {
				logger.info("New col: " + col);
				col++;
			}
		}
		
		//create HBox
		manageGame = new HBox();
		charType = new Label("Player code: ");
		charTypeInput = new TextField();
		startGame = new Button("Start game");
		
		manageGame.getChildren().add(charType);
		manageGame.getChildren().add(charTypeInput);
		manageGame.getChildren().add(startGame);
		manageGame.setSpacing(20);
		
	}
	
	public GridPane getGridPane() {
		return playField;
	}

	public String getPlayerCode() {
		return this.charTypeInput.getText();
	}
	
	public static ArrayList<TTTButton> getButtons(){
		return buttons;
	}

}