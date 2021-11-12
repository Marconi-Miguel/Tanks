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


	public ClientsideThread(Player localPlayer, String serverIP, int serverPort) {
		this.localPlayer = localPlayer;
		this.serverIP = serverIP;
		this.serverPort = serverPort;
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

//////////Messaging////////////////////////////////////////
	private void processMessage(DatagramPacket packet) {
		String msg = new String(packet.getData()).trim();
		switch(msg) {
		case NetworkCodes.CONNECT:
			handleConnection();
		break;
		///
		case NetworkCodes.DISCONNECT:
			handleDisconnection();
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
		System.out.println("[CLIENT] Connected to "+serverIP+" as "+localPlayer.username);
		connected = true;
	}

	private void handleDisconnection() {
		System.out.println("[CLIENT] Disconnected.");
		connected = false;
		this.end = true;
	}

//////////// network syncing //////////////////////////////////////////

}
