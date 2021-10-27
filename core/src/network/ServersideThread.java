package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServersideThread extends Thread {
	
	private DatagramSocket socket;
	private boolean fin = false;
	
	private ServerClient[] clients = new ServerClient[4];
	
	public ServersideThread() {
		try {
			socket = new DatagramSocket(9995);
			System.out.println("Servidor creado");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		do {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data,data.length);
			try {
				System.out.println("Server standing by");
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
		switch(msg) {
		case "connect":
			handleConnection(packet);
		break;
		///
		case "disconnect":
			handleDisconnection(packet);
		break;
		///
		default:
			sendMessage("error",packet.getAddress(),packet.getPort());
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
			if(clients[i].IP==ip) {return true;}
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
	
	public void addClient(InetAddress ip, int port) {
		for (int i=0;i<clients.length;i++) {
			if(clients[i]==null) {
				ServerClient newClient = new ServerClient(ip, port);
				clients[i] = newClient;
				break;
			}
		}
	}
	
	public void removeClient(int index) {
		//TODO: Consider some kind of dispose() ?
		clients[index] = null;
	}
	
	public void disconnectClient(int index) {
		sendMessage("disconnected",clients[index].IP,clients[index].port);
		removeClient(index);
	}
	
	
	////////////processMessage functions//////////////////////////////////////////
	private void handleConnection(DatagramPacket packet) {
		if(slotAvailable()) {
			addClient(packet.getAddress(),packet.getPort());
			sendMessage("connected",packet.getAddress(),packet.getPort());
		}else {
			sendMessage("connectionRejected",packet.getAddress(),packet.getPort());
		}
	}
	
	private void handleDisconnection(DatagramPacket packet) {
		removeClient(getClientID(packet.getAddress()) );
	}
	
	
	//////////
}
