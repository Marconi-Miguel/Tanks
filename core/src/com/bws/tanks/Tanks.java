package com.bws.tanks;

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
		Serverside.getHs().stopServer();
		super.dispose();
		Render.batch.dispose();
	}
}
