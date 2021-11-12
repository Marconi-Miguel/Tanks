package com.bws.tanks;

import java.net.DatagramSocket;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import network.Serverside;
import screens.MapScreen;
import utilities.Render;

public class Tanks extends Game {
	// hmmmm
	
	@Override
	public void create () {
		Render.app = this;
		Render.batch = new SpriteBatch();
		this.setScreen(new MapScreen());
	
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () { //Called when game closes!
		DatagramSocket serverSocket = Serverside.getSocket();
		if (serverSocket != null && !serverSocket.isClosed()) {
			System.out.println("[SERVER] Closing socket on port "+serverSocket.getLocalPort()); //Close socket currently in use.
			serverSocket.close();
		}
		super.dispose();
		Render.batch.dispose();
	}
}
