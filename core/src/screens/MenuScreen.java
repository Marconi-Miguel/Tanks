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
import utilities.ClientRender;
import utilities.Config;
import utilities.Resources;

public class MenuScreen implements Screen {
	private Sprite bg;
	private Sprite banner;
	private Sprite parchment;
	private Player localPlayer;
	private SpriteBatch b;
	private boolean connected;
	private ButtonText[] menuTexts = new ButtonText[2];
	private ButtonText connect;
	private float time;
	private MenuScene scene;
	private InputMultiplexer multiPlexer;
	private ButtonText warningText;

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

		b = ClientRender.batch;
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

		warningText = new ButtonText(Resources.FONT, 20, Color.WHITE, true);
		warningText.setText("B&W INC");
		warningText.setPosition(0, 20);
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
		warningText.draw();
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
			try {
				localPlayer.setUsername(scene.getUsername());
				String[] serverIp = scene.getIp().split(":");
				if (localPlayer.connect(serverIp[0], Integer.parseInt(serverIp[1]))) {
					ClientRender.app.music.setVolume(3);
					ClientRender.app.setScreen(new MapScreen(localPlayer));
				} else {
					warningText.setText("ERROR TRYING TO CONNECT TO THE SERVER");
					warningText.setColor(Color.RED);
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
