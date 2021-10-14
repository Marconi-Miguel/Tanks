package com.bws.tanks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bws.tanks.Tanks;

import utilities.Config;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.height= Config.HEIGHT;
		config.width = Config.WIDTH;
		new LwjglApplication(new Tanks(), config);
	}
}

