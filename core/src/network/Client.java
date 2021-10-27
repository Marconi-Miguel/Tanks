package network;

public class Client {
	
	private ClientThread hc;
	boolean bandera = true;
	
	public Client() {
	}
	
	public void startConnection(String serverIP) {
		hc = new ClientThread(serverIP);
		hc.start();
	}

	public ClientThread getThread() {
		return hc;
	}
}
