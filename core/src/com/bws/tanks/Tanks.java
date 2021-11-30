package com.bws.tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import input.Player;
import network.Clientside;
import network.ClientsideThread;
import screens.MapScreen;
import utilities.ClientRender;
import utilities.Resources;

public class Tanks extends Game {
	public static AssetManager manager;
	public Music music;
	@Override
	public void create () {
		ClientRender.app = this;
		ClientRender.batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load(Resources.MENUTHEME,Music.class);
		manager.finishLoading();
		music = manager.get(Resources.MENUTHEME, Music.class);
		music.setLooping(true);
		music.play();		
		music.setVolume(0.4f);
		this.setScreen(new MapScreen(new Player()));

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
		
		ClientRender.batch.dispose();
	}
}
