package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import input.InputKeys;
import input.Player;

public class ClientsideThread extends Thread {
	
	private DatagramSocket socket;
	private boolean end = false;
	private String serverIP;
	private Player playerClient;

	
	public ClientsideThread(Player playerClient, String serverIP) {
		this.playerClient = playerClient;
		try {
			socket = new DatagramSocket();
			this.serverIP = serverIP;
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
		DatagramPacket packet = new DatagramPacket(data,data.length,ipServer,9995);
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
////////////processMessage functions//////////////////////////////////////////
	
	private void handleDisconnection() {
		System.out.println("disconnected");
		this.end = true;
	}

//////////// network syncing //////////////////////////////////////////
	
	private void sendInputs() {
		//Inputs are sent as a String in the following order: RIGHT/LEFT/UP/DOWN/FIRE: The msg looks like: INPUT-boolean/boolean/boolean/boolean/boolean
		//where each boolean is one of the input values.
		sendMessage(NetworkCodes.INPUT);
	}
}
