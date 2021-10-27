package network;

public class Serverside {
	
	private ServersideThread hs;
	
	public Serverside() {
		hs = new ServersideThread();
		hs.start();
	}

	public ServersideThread getHs() {
		return hs;
	}
}
