package input;

import network.Clientside;
import network.ClientsideThread;
import utilities.Render;

public class PlayerNetworkManager {
	Clientside client;
	ClientsideThread thread;
	
	
	public PlayerNetworkManager() {
		client = new Clientside();
		thread = client.getThread();
	}
	
	
}
