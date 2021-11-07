package network;

import input.Player;

public class Clientside {
	
	private ClientsideThread hc;
	Player playerClient; //The player linked to this client.
	
	public Clientside(Player playerClient) {
		this.playerClient = playerClient;
	}
	
	public void startConnection(String serverIP) {
		hc = new ClientsideThread(playerClient,serverIP);
		hc.start();
	}

	public ClientsideThread getThread() {
		return hc;
	}
}
