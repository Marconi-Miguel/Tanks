package com.bws.tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import network.Clientside;
import network.ClientsideThread;
import network.Serverside;
import network.ServersideThread;
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
		ClientsideThread cThread = Clientside.getThread();
		if (cThread != null) {cThread.disconnect();}
		ServersideThread sThread = Serverside.getHs();
		if(sThread != null) {sThread.stopServer();}
		super.dispose();
		Render.batch.dispose();
	}
}
