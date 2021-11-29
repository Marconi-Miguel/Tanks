package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import elements.Tank;
import input.Player;
import utilities.Render;

public class ClientsideThread extends Thread {

	private DatagramSocket socket;
	private boolean end, connected = false;
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
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
				processMessage(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!end);
	}

//////////Messaging///////////////////////////////////////
	private void processMessage(DatagramPacket packet) {
		String msg = new String(packet.getData()).trim();
		String networkCode = msg.substring(0, NetworkCodes.CODELENGTH); // The first part of the message is the network
																		// code.
		String args = msg.substring(NetworkCodes.CODELENGTH, msg.length()); // Everything after the network code are the
																			// arguments (args) of the network message.
		System.out.println(msg);

		switch (networkCode) {
		case NetworkCodes.CONNECT:
			handleConnection();
			break;
		///
		case NetworkCodes.DISCONNECT:
			handleDisconnection(args);
			break;
		///
		case NetworkCodes.PING: // Ping, are you there?
			sendMessage(NetworkCodes.PONG); // PONG! I'm still here!
			break;
		///
		case NetworkCodes.NEWTANK:
			createPlayerTank(args);
		break;
		///
		case NetworkCodes.TANKSYNC:
			syncPlayerTank(args);
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
			System.out.println("error sending message.");
		}
		DatagramPacket packet = new DatagramPacket(data, data.length, ipServer, serverPort);

		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

////////////processMessage functions//////////////////////////////////////////

	private void handleConnection() {
		System.out.println("[CLIENT] Connected to " + serverIP + ":" + serverPort + " as " + localPlayer.username);
		connected = true;
	}

	private void handleDisconnection(String args) {
		System.out.println("[CLIENT] Disconnected: " + args);
		connected = false;
		this.end = true;
	}

	private void syncPlayerTank(String argumentString) {
		String[] args = argumentString.split("/"); // Split the data packed in the string.
		ArrayList<Tank> tanks = Render.tanks;
		Tank tank = null;
		Boolean found;
		int cont = 0;
		if(Render.tanks.size()==0) {return;} //TODO hotfix
		do {
			
			found = false;
			
			if (tanks.get(cont) != null && tanks.get(cont).owner.username.equals(args[0])) {
				tank = tanks.get(cont);
				found = true;
			} // find the tank owned by this player
			cont++;
		} while (!found || cont < tanks.size());

		if (tank == null) {
			return;
		}
		
		
		
		if(tank.hull.getX() != Float.parseFloat(args[1]) || tank.hull.getY() != Float.parseFloat(args[2])) {
			tank.correction = true;
			tank.correction(cont, cont);
		}

		tank.hull.rotation=Float.parseFloat(args[3]);
		
//		if (Boolean.parseBoolean(args[4])) {
//			tank.hull.inRoad();
//		}else {
//			tank.hull.outRoad();
//		}
		
		
		System.out.println(tank.hull.getX());
		System.out.println(tank.hull.getY());
		System.out.println(tank.hull.rotation);
	}
	
	private void createPlayerTank(String args) {
		
	}

//////////// connection //////////////////////////////

	public boolean connect(String ip, int port) {
		if (connected) {
			return false;
		} // Unable to connect, because we're connected already.
		this.serverIP = ip;
		this.serverPort = port;
		int connectionAttempts = 0;

		do {
			sendMessage(NetworkCodes.CONNECT + localPlayer.username);// Attempt connection
			connectionAttempts++;
			try {
				Thread.sleep(500); // wait a second.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!connected && connectionAttempts < 5);

		if (connected) {
			return true;
		} else {
			System.out.println("[CLIENT] Failed to connect to " + ip + ":" + port);
			return false;
		}
	}

	public void disconnect() {
		if (connected) {
			sendMessage(NetworkCodes.DISCONNECT);
		}
	}

}
