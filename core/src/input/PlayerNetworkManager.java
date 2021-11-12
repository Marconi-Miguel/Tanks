package input;

import network.Clientside;
import network.ClientsideThread;

public class PlayerNetworkManager {
	Clientside client;
	ClientsideThread thread;
	
	
	public PlayerNetworkManager(Player localPlayer) {
		client = new Clientside(localPlayer);
		thread = client.getThread();
	}
	
	
}
