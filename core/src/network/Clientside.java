package network;

import input.Player;
import utilities.Render;

public class Clientside {
	
	private ClientsideThread hc;
	Player playerClient; //The player linked to this client.
	
	public Clientside() {
		this.playerClient = Render.player;
	}
	
	public void startConnection(String serverIP) {
		hc = new ClientsideThread(playerClient,serverIP);
		hc.start();
	}

	public ClientsideThread getThread() {
		return hc;
	}
}
