package input;

import java.util.HashMap;
import java.util.Map;

import elements.Tank;
import network.Clientside;
import network.ClientsideThread;
import network.NetworkCodes;

public class Player {
	
	String username;
	public static PlayerInputManager PIM;
	public static PlayerNetworkManager PNM;
	Tank playerTank;
	
	///Network
	Clientside localClient;
	ClientsideThread thread;
	
	//Input
	
	
	public Map<InputKeys, Boolean>  inputs = new HashMap<InputKeys, Boolean>(); //make a map (dictionary) that accepts booleans named as the input enums. (huh?)
	private void initializeInputs() {
		InputKeys[] array = InputKeys.values(); //Take all the enums InputKeys and put them in an array.
		for(int i=0; i<array.length;i++) { 
			inputs.put(array[i], false); //Use the array of enums to put each enum in the inputs list.
		}
	}

	
	public Player(String username) {
		PIM = new PlayerInputManager(this);
		PNM = new PlayerNetworkManager(this);
		initializeInputs();
		this.username = username;
		localClient = new Clientside(this);
	}
	
	///Input functions
	
	
	///Network functions
	
	public void connect(String IP) {
		if (thread == null){
			localClient.startConnection(IP);
			thread = localClient.getThread();
			thread.sendMessage(NetworkCodes.CONNECT+username);
		}
	}
}
