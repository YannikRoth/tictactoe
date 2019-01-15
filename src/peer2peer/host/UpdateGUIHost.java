package peer2peer.host;


public class UpdateGUIHost extends Thread{
	private THostView view;
	private int btnID;
	private String playerChar;
	
	public UpdateGUIHost(THostView view, int btnID, String playerChar) {
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
