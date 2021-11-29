package network;

import input.Player;

public class Clientside {

	private static ClientsideThread hc;
	Player localPlayer; // The player linked to this client.

	public Clientside(Player localPlayer) {
		this.localPlayer = localPlayer;
	}

	public void startThread() {
		hc = new ClientsideThread(localPlayer);
		hc.start();
	}

	public static ClientsideThread getThread() {
		return hc;
	}
}
