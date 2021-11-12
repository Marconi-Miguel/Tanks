package input;

import network.Clientside;
import network.ClientsideThread;

public class PlayerNetworkManager {
	Clientside client;
	
	
	public PlayerNetworkManager(Player localPlayer) {
		client = new Clientside(localPlayer);
	}
	
	
}
