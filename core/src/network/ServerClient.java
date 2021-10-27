package network;

import java.net.InetAddress;

import elements.Tank;

public class ServerClient {
	
	String username;
	Tank tank;
	InetAddress IP;
	int port;
	
	
	
	
	public ServerClient(InetAddress ip, int port) {
		this.IP = ip;
		this.port = port;
	}
	

}

