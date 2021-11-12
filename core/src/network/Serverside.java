package network;

import java.net.DatagramSocket;

public class Serverside {
	
	private static ServersideThread hs;
	
	public Serverside() {
		hs = new ServersideThread();
		hs.start();
	}

	public static ServersideThread getHs() {
		return hs;
	}
	
	public static DatagramSocket getSocket() {
		return(hs.getSocket() );
	}
}
