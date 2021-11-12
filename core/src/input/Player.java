package input;

import network.Clientside;
import network.ClientsideThread;
import network.NetworkCodes;

public class Player extends Client{ //The player is a "local client"
	
	public static PlayerInputManager PIM;
	public static PlayerNetworkManager PNM;
	
	///Network
	Clientside localClient;
	ClientsideThread thread;
	
	//public Map<InputKeys, Boolean>  inputs = new HashMap<InputKeys, Boolean>(); //make a map (dictionary) that accepts booleans named as the input enums. (huh?)


	
	public Player(String username) {
		PIM = new PlayerInputManager(this);
		PNM = new PlayerNetworkManager();
		this.username = username;
		localClient = new Clientside();
	}
	
	///Input functions
	
	
	///Network functions
	
	public void connect(String IP) {
		if (thread == null){
			localClient.startConnection(IP);
			thread = localClient.getThread();
			PIM.thread = thread;
			thread.sendMessage(NetworkCodes.CONNECT+username);
		}
	}
}
