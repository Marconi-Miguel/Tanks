package network;

import input.Player;

public class Clientside {
	
	private ClientsideThread hc;
	Player localPlayer; //The player linked to this client.
	
	public Clientside(Player localPlayer) {
		this.localPlayer = localPlayer;
	}
	
	public void startConnection(String serverIP, int serverPort) {
		hc = new ClientsideThread(localPlayer,serverIP,serverPort);
		hc.start();
	}

	public ClientsideThread getThread() {
		return hc;
	}
}
