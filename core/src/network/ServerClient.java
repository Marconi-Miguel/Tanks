package network;

import java.net.InetAddress;

import input.Client;

public class ServerClient extends Client{
	
	InetAddress IP;
	int port;
	
	public ServerClient(InetAddress ip, int port) {
		this.IP = ip;
		this.port = port;
	}
	

}

