package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import input.Player;

public class ClientsideThread extends Thread {

	private DatagramSocket socket;
	private boolean end,connected = false;
	private String serverIP;
	private int serverPort;
	private Player localPlayer;


	public ClientsideThread(Player localPlayer) {
		this.localPlayer = localPlayer;
		try {
			socket = new DatagramSocket();
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
				socket.receive(packet);
				processMessage(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(!end);
	}

//////////Messaging///////////////////////////////////////
	private void processMessage(DatagramPacket packet) {
		String msg = new String(packet.getData()).trim();
		//System.out.println("[CLIENT RECEIVED] "+msg);
		String args = msg.substring(NetworkCodes.CODELENGTH,msg.length());
		String networkCode = msg.substring(0,NetworkCodes.CODELENGTH);
		switch(networkCode) {
		case NetworkCodes.CONNECT:
			handleConnection();
		break;
		///
		case NetworkCodes.DISCONNECT:
			handleDisconnection(args);
		break;
		///
		case NetworkCodes.PING: //Ping, are you there?
			sendMessage(NetworkCodes.PONG); //PONG! I'm still here!
		break;
		///
		}
	}

	public void sendMessage(String msg) {
		byte[] data = msg.getBytes();
		InetAddress ipServer = null;
		try {
			ipServer = InetAddress.getByName(serverIP);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		DatagramPacket packet = new DatagramPacket(data,data.length,ipServer,serverPort);

		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

////////////processMessage functions//////////////////////////////////////////
	
	private void handleConnection() {
		System.out.println("[CLIENT] Connected to "+serverIP+":" +serverPort+" as "+localPlayer.username);
		connected = true;
	}

	private void handleDisconnection(String args) {
		System.out.println("[CLIENT] Disconnected: "+args);
		connected = false;
		this.end = true;
	}

//////////// connection //////////////////////////////
	
	public boolean connect(String ip, int port) {
		if (connected) {return false;} //Unable to connect, because we're connected already.
		this.serverIP = ip;
		this.serverPort = port;
		int connectionAttempts = 0;
		
		do {
			sendMessage(NetworkCodes.CONNECT+localPlayer.username);//Attempt connection
			connectionAttempts++;
			try {
				Thread.sleep(500); //wait a second.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(!connected && connectionAttempts < 5);
		
		if(connected) {return true;} else {
			System.out.println("[CLIENT] Failed to connect to "+ip+":"+port);
			return false; 
		}
	}
	
	public void disconnect() {
		if(connected) {
			sendMessage(NetworkCodes.DISCONNECT);
		}
	}

}
