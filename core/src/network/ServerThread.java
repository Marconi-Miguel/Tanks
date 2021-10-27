package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerThread extends Thread {
	
	private DatagramSocket socket;
	private boolean fin = false;
	
	private InetAddress[] clientIP = new InetAddress[4];
	private int[] clientPort = new int[4];
	
	public ServerThread() {
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
		for (int i=0;i<clientIP.length;i++) {
			sendMessage(msg,clientIP[i],clientPort[i]);
		}
	}
	
//////////////Client validation////////////////////////////////////
	
	public boolean isClient(InetAddress ip) {
		for (int i=0;i<clientIP.length;i++) {
			if(clientIP[i]==ip) {return true;}
		}
		return false;
	}
	
	public int getClientIndex(InetAddress ip) {
		for (int i=0;i<clientIP.length;i++) {
			if(clientIP[i]==ip) {return i;}
		}
		return -1;
	}
	
	public boolean slotAvailable() {
		for (int i=0;i<clientIP.length;i++) {
			if(clientIP[i]==null) {return true;}
		}
		return false;
	}
	
	public void addClient(InetAddress ip, int port) {
		for (int i=0;i<clientIP.length;i++) {
			if(clientIP[i]==null) {
				clientIP[i] = ip;
				clientPort[i] = port;
				break;
			}
		}
	}
	
	public void removeClient(int index) {
		clientIP[index] = null;
		clientPort[index] = -1;
	}
	
	public void disconnectClient(int index) {
		sendMessage("disconnected",clientIP[index],clientPort[index]);
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
		removeClient(getClientIndex(packet.getAddress()) );
	}

}
