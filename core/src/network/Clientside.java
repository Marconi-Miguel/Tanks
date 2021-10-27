package network;

public class Clientside {
	
	private ClientsideThread hc;
	
	public Clientside() {
	}
	
	public void startConnection(String serverIP) {
		hc = new ClientsideThread(serverIP);
		hc.start();
	}

	public ClientsideThread getThread() {
		return hc;
	}
}
