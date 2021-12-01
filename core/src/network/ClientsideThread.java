package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

import elements.ClientsideSprite;
import input.Client;
import input.Player;
import utilities.ClientRender;

public class ClientsideThread extends Thread {

	private DatagramSocket socket;
	private boolean end, connected = false;
	private String serverIP;
	private int serverPort;
	private Player localPlayer;
	public ArrayList<Client> clientList;
	private long lastResyncTick;

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
				
			}
		} while (!end);
	}

//////////Messaging///////////////////////////////////////
	private void processMessage(DatagramPacket packet) {
		String msg = new String(packet.getData()).trim();
		String networkCode = msg.substring(0, NetworkCodes.CODELENGTH); // The first part of the message is the network
																		// code.
		String argumentString = msg.substring(NetworkCodes.CODELENGTH, msg.length()); // Everything after the network
																						// code are the arguments (args)
																						// of the network message.
		String[] args = argumentString.split("-");
		if (!networkCode.equals(NetworkCodes.PING) && !networkCode.equals(NetworkCodes.UPDATESPRITE)) {
			System.out.println(msg);
		}

		switch (networkCode) {
		case NetworkCodes.CONNECT:
			handleConnection(args);
			break;
		///
		case NetworkCodes.DISCONNECT:
			handleDisconnection(args);
			break;
		///
		case NetworkCodes.PING: // Ping, are you there?
			if(Integer.parseInt(args[0]) != ClientRender.renderList.size() && Long.parseLong(args[1]) - localPlayer.firstTick > 100){
				sendMessage(NetworkCodes.RENDERSYNC);
			}
			sendMessage(NetworkCodes.PONG); // PONG! I'm still here!
			break;
		///
		case NetworkCodes.NEWSPRITE:
			addSprite(args);
			break;
		///
		case NetworkCodes.UPDATESPRITE:
			updateSprite(args);
			break;
		///
		case NetworkCodes.REMOVESPRITE:
			ClientRender.removeSprite(Integer.parseInt(args[0]));
			break;
		///
		case NetworkCodes.EXPLOSION:
			ClientRender.addAnimation(args);
			break;
		///
		case NetworkCodes.RENDERSYNC: //Reset the renderList so we can resync!
			handleResync(args);
			break;
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
			
		}
	}

////////////processMessage functions//////////////////////////////////////////

	private void handleConnection(String[] args) {
		System.out.println("[CLIENT] " + args[0]);
		lastResyncTick = 0; //Since it's a new connection, the client has never had to re-sync.
		connected = true;
	}

	private void handleDisconnection(String[] args) {
		System.out.println("[CLIENT] Disconnected: " + args[0]);
		connected = false;
		this.end = true;
	}
	
	private void handleResync(String[] args) {
		if(Long.parseLong(args[0])-lastResyncTick > 50) //If it's been more than 50 ticks since last resync request, allow this resync.
		for (int i = 0; i < ClientRender.renderList.size(); i++) { 
			ClientRender.renderList.get(i).remove();//Remove everything inside the render list.
		}
		lastResyncTick = Long.parseLong(args[0]);
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
////////sprite management
	
	private void addSprite(final String[] args) {
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				ClientRender.addSprite(args);
			}
		});
	}
	
	private void updateSprite(final String[] args) {
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				ClientRender.updateSprite(args);
			}
		});
	}
}
