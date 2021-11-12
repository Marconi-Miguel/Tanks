package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServersideThread extends Thread {
	
	private DatagramSocket socket;
	private boolean fin,serverCreated = false;
	int serverAddress = 9995;
	
	private ServerClient[] clients = new ServerClient[4];
	
	public ServersideThread() {
		serverCreated = startServer();
	}
	
	private boolean startServer() { //attempt to create a datagram socket.
		try {
			socket = new DatagramSocket(serverAddress);
			System.out.println("SOCKET ESTABLISHED ON "+serverAddress);
			serverCreated = true;
			return true; //server created.
		} catch (SocketException e) {
			//e.printStackTrace(); too much spam
			System.out.println("WARNING: UNABLE TO CREATE SOCKET ON "+serverAddress+" ... RETRYING");
			return false; //unable to create
		}
	}
	
	private void checkSocket() {//TODO: Make sure the socket check doesn't become an infinite loop.
		//while (!serverCreated) { //If the socket is unexistant. 
			
			serverCreated = startServer(); //attempt to create a socket.
			try {
				Thread.sleep(1000); //wait a second before checking again.
				serverAddress++;
				startServer();
			} catch (InterruptedException e) {
				e.printStackTrace();
				startServer();//retry anyways.
			}
		//}//do not continue until it is created.
	}
	
	@Override
	public void run() { 
		while (!serverCreated) {
			checkSocket(); //Make sure a socket actually exists before anything else.
		}
		do {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data,data.length);
			try {
				System.out.println("Server standing by.");
				socket.receive(packet);
				processMessage(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(!fin);
	}

//////////Messaging////////////////////////////////////////
	private void processMessage(DatagramPacket packet) {
		String msg = new String(packet.getData()).trim();
		String args = msg.substring(NetworkCodes.CODELENGTH,msg.length()); //Everything after the network code are the arguments (args) of the network message.
		String networkCode = msg.substring(0,NetworkCodes.CODELENGTH); //The first part of the message is the network code.
		if (!isClient(packet.getAddress()) && networkCode != NetworkCodes.CONNECT){ //If someone who's not a client attempts to do something other than connect.
			sendMessage(NetworkCodes.FORBIDDEN,packet.getAddress(),packet.getPort()); //Notify the client they're not allowed to send message.
			return; //end process.
		}
		switch(networkCode) { //switches the network code.
		case NetworkCodes.CONNECT: //connect
			handleConnection(packet,args);
		break;
		///
		case NetworkCodes.DISCONNECT: //disconnect
			handleDisconnection(packet,args);
		break;
		///
		case NetworkCodes.INPUT:
			handleUserInput(packet,args);
		break;
		///
		default:
			sendMessage("unknown error",packet.getAddress(),packet.getPort());
		break;
		}
	}

	public void sendMessage(String msg, InetAddress ip, int port) {
		byte[] data = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(data,data.length, ip, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void broadcast(String msg) { //send message to all connected clients.
		for (int i=0;i<clients.length;i++) {
			sendMessage(msg,clients[i].IP,clients[i].port);
		}
	}
	
//////////////Client validation////////////////////////////////////
	
	public boolean isClient(InetAddress ip) {
		for (int i=0;i<clients.length;i++) {
			if(clients[i] != null && clients[i].IP==ip) {return true;}
		}
		return false;
	}
	
	public int getClientID(InetAddress ip) {
		for (int i=0;i<clients.length;i++) {
			if(clients[i].IP==ip) {return i;}
		}
		return -1;
	}
	
	public boolean slotAvailable() {
		for (int i=0;i<clients.length;i++) {
			if(clients[i]==null) {return true;}
		}
		return false;
	}
	
	public void addClient(InetAddress ip, int port, String username) {
		for (int i=0;i<clients.length;i++) {
			if(clients[i]==null) {
				ServerClient newClient = new ServerClient(ip, port);
				newClient.username = username;
				clients[i] = newClient;
				break;
			}
		}
	}
	
	public void removeClient(int id) {
		//TODO: Consider some kind of dispose() ?
		clients[id] = null;
	}
	
	public void disconnectClient(int index) {
		sendMessage("disconnected",clients[index].IP,clients[index].port);
		removeClient(index);
	}
	
	
	////////////processMessage functions//////////////////////////////////////////
	private void handleConnection(DatagramPacket packet, String args) {
		if(slotAvailable()) {
			addClient(packet.getAddress(),packet.getPort(), args);
			sendMessage("connected",packet.getAddress(),packet.getPort());
		}else {
			sendMessage("connectionRejected",packet.getAddress(),packet.getPort());
		}
	}
	
	private void handleDisconnection(DatagramPacket packet, String msg) {
		removeClient(getClientID(packet.getAddress()) );
	}
	
	private void handleUserInput(DatagramPacket packet, String packagedArgs) { //packaged args is the string with multiple arguments divided with /
		String[] args = packagedArgs.split("/");
		ServerClient requestingClient = null;
		
	}
	
	//////////
}
