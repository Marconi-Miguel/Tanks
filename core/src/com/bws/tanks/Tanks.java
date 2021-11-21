package com.bws.tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import network.Clientside;
import network.ClientsideThread;
import screens.MenuScreen;
import utilities.Render;

public class Tanks extends Game {
	
	@Override
	public void create () {
		Render.app = this;
		Render.batch = new SpriteBatch();
		this.setScreen(new MenuScreen());
	
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () { //Called when game closes!
		super.dispose();
		ClientsideThread cThread = Clientside.getThread();
		if (cThread != null) {cThread.disconnect();}
		
		Render.batch.dispose();
	}
}
