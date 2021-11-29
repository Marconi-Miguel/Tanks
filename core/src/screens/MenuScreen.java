package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import elements.ButtonText;
import input.Player;
import scenes.MenuScene;
import utilities.Config;
import utilities.Render;
import utilities.Resources;

public class MenuScreen implements Screen {
	Sprite bg;
	Sprite banner;
	Sprite parchment;
	Player localPlayer;
	SpriteBatch b;
	boolean connected;
	ButtonText[] menuTexts = new ButtonText[2];
	ButtonText connect;

	float time;
	MenuScene scene;
	InputMultiplexer multiPlexer;

	public MenuScreen() {

		localPlayer = new Player();
		multiPlexer = new InputMultiplexer();
		scene = new MenuScene();
		multiPlexer.addProcessor(localPlayer.PIM);
		multiPlexer.addProcessor(scene.stage);
		Gdx.input.setInputProcessor(multiPlexer);
	}

	@Override
	public void show() {

		bg = new Sprite(new Texture(Resources.BG));
		bg.setSize(Config.WIDTH, Config.HEIGHT);

		b = Render.batch;
		for (int i = 0; i < menuTexts.length; i++) {
			menuTexts[i] = new ButtonText(Resources.FONT, 12, Color.WHITE, true);
		}

		menuTexts[0].setText("Conectarse al Servidor");
		menuTexts[1].setText("Salir");

		float separacion = 10 + menuTexts[0].getHeight();
		banner = new Sprite(new Texture(Resources.BANNER));
		banner.setSize(menuTexts[0].getWidth() * 1.2f, menuTexts[0].getHeight() * 15);
		banner.setPosition(10, Config.HEIGHT / 1.5f - menuTexts.length * 2);
		for (int i = 0; i < menuTexts.length; i++) {
			menuTexts[i].setPosition(getHalfX(banner) - menuTexts[i].getWidth() / 2,
					getTopY(banner) - 20 - i * separacion);
		}
		parchment = new Sprite(new Texture(Resources.PARCHMENT));
		parchment.setSize(400, 250);
		parchment.setPosition(Config.WIDTH / 2 - parchment.getWidth() / 2,
				Config.HEIGHT / 2 - parchment.getHeight() / 2);
		connect = new ButtonText(Resources.FONT, 12, Color.GRAY, true);
		connect.setText("Connect");
		connect.setPosition(Config.WIDTH / 2 - connect.getWidth() / 2, Config.HEIGHT / 2 - parchment.getHeight() / 7f);

	}

	public float getHalfX(Sprite s) {
		return s.getX() + s.getWidth() / 2;
	}

	public float getTopY(Sprite s) {
		return s.getY() + s.getHeight();
	}

	@Override
	public void render(float delta) {
		update(delta);
		b.begin();
		bg.draw(b);
		banner.draw(b);
		menuTexts[0].draw();
		menuTexts[1].draw();
		b.end();
		if (connected) {
			inputConnect();
		}

	}

	private void inputConnect() {
		b.begin();
		parchment.draw(b);
		connect.draw();
		b.end();
		scene.draw();

	}

	private void update(float delta) {
		time += delta;
		mouseInputs();
		if (menuTexts[0].isPressed()) {

			connected = !connected;
			menuTexts[0].reset();
		} else if (menuTexts[1].isPressed()) {
			Gdx.app.exit();
		} else if (connect.isPressed()) {
			// TODO: localPlayer.setUsername(scene.getUsername());
			try {
				int port;
				if(scene.getPort().equals("")) {
					port = 9995; //set to default port
				}else {
					port = Integer.parseInt(scene.getPort());
				}
				if (localPlayer.connect(scene.getIp(), port) ) {
					Render.app.music.stop();
					Render.app.setScreen(new MapScreen(localPlayer));
				} else {
					System.out.println("else");
					connect.reset();
					// TODO: Mostrar que no se pudo conectar.
				}
			} catch (Exception e) {

			}

		}

	}

	private void mouseInputs() {
		hoverButtons();

		if (localPlayer.PIM.isClick() && time > 0.2) {
			time = 0;
			for (int i = 0; i < menuTexts.length; i++) {
				menuTexts[i].update(localPlayer.PIM.getMouseX(), localPlayer.PIM.getMouseY());
			}
			connect.update(localPlayer.PIM.getMouseX(), localPlayer.PIM.getMouseY());

		}
	}

	private void hoverButtons() {
		for (int i = 0; i < menuTexts.length; i++) {
			menuTexts[i].updateHover(localPlayer.PIM.getMouseX(), localPlayer.PIM.getMouseY());
		}
		connect.updateHover(localPlayer.PIM.getMouseX(), localPlayer.PIM.getMouseY());
	}

	@Override
	public void resize(int width, int height) {
		// note, not resize with buttons!

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
