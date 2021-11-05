package network;

import java.net.InetAddress;
import java.util.Map;

import elements.Tank;
import input.InputKeys;

public class ServerClient {
	
	String username;
	Tank tank;
	InetAddress IP;
	int port;
	
	//Input
	public Map<InputKeys, Boolean> inputs;
	
	
	
	
	public ServerClient(InetAddress ip, int port) {
		this.IP = ip;
		this.port = port;
	}
	

}

