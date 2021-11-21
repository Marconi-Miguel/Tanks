package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import elements.ButtonText;
import input.Player;
import utilities.Config;
import utilities.Render;
import utilities.Resources;

public class MenuScreen implements Screen {
	Sprite bg;
	Sprite banner;
	Player localPlayer;
	SpriteBatch b;
	ButtonText[] texts = new ButtonText[2];
	float time;
	
	Music menuTheme = Gdx.audio.newMusic(Gdx.files.internal(Resources.MENUTHEME));

	public MenuScreen() {
		localPlayer = new Player("testPlayer");
		Gdx.input.setInputProcessor(localPlayer.PIM);
	}

	@Override
	public void show() {
		menuTheme.play();
		menuTheme.setLooping(true);

		bg = new Sprite(new Texture(Resources.BG));
		bg.setSize(Config.WIDTH, Config.HEIGHT);

		b = Render.batch;
		texts[0] = new ButtonText(Resources.FONT, 12, Color.WHITE, true);
		texts[1] = new ButtonText(Resources.FONT, 12, Color.WHITE, true);

		texts[0].setText("Conectarse al Servidor");
		texts[1].setText("Salir");
		float separacion = 10 + texts[0].getHeight();
		System.out.println(Config.HEIGHT);
		banner = new Sprite(new Texture(Resources.BANNER));
		banner.setSize(texts[0].getWidth() * 1.2f, texts[0].getHeight() * 15);
		banner.setPosition(10, Config.HEIGHT /1.5f - texts.length * 2);
		for (int i = 0; i < texts.length; i++) {
			texts[i].setPosition(getHalfX(banner) - texts[i].getWidth() / 2, getTopY(banner) - 20 - i * separacion);
		}


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
		texts[0].draw();
		texts[1].draw();
		b.end();
	}

	private void update(float delta) {
		time += delta;
		mouseInputs();
		if(texts[0].isPressed()) {
			Render.app.setScreen(new MapScreen());
		}else if(texts[1].isPressed()) {
			Gdx.app.exit();
		}
		
		

	}

	private void mouseInputs() {
		hoverButtons();

		if (localPlayer.PIM.isClick() && time > 0.2) {
			time = 0;
			for (int i = 0; i < texts.length; i++) {
				texts[i].update(localPlayer.PIM.getMouseX(), localPlayer.PIM.getMouseY());
			}
			
		}
	}

	private void hoverButtons() {
		for (int i = 0; i < texts.length; i++) {
			texts[i].updateHover(localPlayer.PIM.getMouseX(), localPlayer.PIM.getMouseY());
		}
	}

	@Override
	public void resize(int width, int height) {
		//note, not resize with buttons!

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
