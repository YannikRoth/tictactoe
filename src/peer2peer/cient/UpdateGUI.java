package peer2peer.cient;

public class UpdateGUI extends Thread{
	
	private TClientView view;
	private int btnID;
	private String playerChar;
	
	public UpdateGUI(TClientView view, int btnID, String playerChar) {
		this.view = view;
		this.btnID = btnID;
		this.playerChar = playerChar;
	}
	
	@Override
	public void run() {
		view.getButtons().forEach(b -> {
			if(b.getButtonID() == this.btnID) {
				b.setText(playerChar);
				b.setDisable(true);
			}
		});
	}


}
