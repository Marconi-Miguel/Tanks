package input;

import network.Clientside;
import network.ClientsideThread;

public class PlayerNetworkManager {
	Clientside client;
	ClientsideThread thread;
	
	
	public PlayerNetworkManager() {
		client = new Clientside();
		thread = client.getThread();
	}
	
	
}
