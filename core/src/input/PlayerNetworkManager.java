package input;

import network.Clientside;
import network.ClientsideThread;

public class PlayerNetworkManager {
	Clientside client;
	ClientsideThread thread;
	
	
	public PlayerNetworkManager(Player player) {
		client = new Clientside(player);
		thread = client.getThread();
	}
	
	
}
