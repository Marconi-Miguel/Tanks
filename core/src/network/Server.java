package network;

public class Server {
	
	private ServerThread hs;
	
	public Server() {
		hs = new ServerThread();
		hs.start();
	}

	public ServerThread getHs() {
		return hs;
	}
}
