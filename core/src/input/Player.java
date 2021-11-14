package input;

import network.Clientside;
import network.ClientsideThread;

public class Player extends Client{ //The player is a "local client"
	
	public static PlayerInputManager PIM;
	public static PlayerNetworkManager PNM;
	
	///Network
	Clientside localClient;
	ClientsideThread thread;
	
	public Player(String username) {
		this.username = username;
		PIM = new PlayerInputManager(this);
		
		PNM = new PlayerNetworkManager(this);
		localClient = PNM.client;
	}
	
	///Input functions
	
	
	///Network functions
	
	public boolean connect(String IP, int port) {
		if (thread == null){
			localClient.startThread();
			thread = localClient.getThread();
			PIM.thread = thread;
			if(!thread.connect(IP, port) ) {
				PIM.thread = null; //failsafe
				thread.interrupt();
				return false; //Unable to connect. No thraed.
			}else { return true; } //Successful connection, thraed exists.
		} else { return true; } //thread already exists, so it IS connected.
	}
}
