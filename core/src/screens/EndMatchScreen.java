package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import elements.ButtonText;
import input.Player;
import utilities.ClientRender;
import utilities.Config;
import utilities.Resources;

public class EndMatchScreen implements Screen{
	private ButtonText menuBt;
	private ButtonText letter;
	private Player player;
	private SpriteBatch b;
	private float time;
	private Sprite bg;
	public EndMatchScreen(boolean b) {
		menuBt = new ButtonText(Resources.FONT,40,Color.WHITE,true);
		letter = new ButtonText(Resources.FONT,40,Color.WHITE,true);
		menuBt.setText("Back to Menu");
		if(b)letter.setText("WINNER");
		if(b)letter.setText("YOU LOSE");
		
		letter.setPosition(letter.getWidth()/2-Config.WIDTH/2, Config.HEIGHT);
		menuBt.setPosition(letter.getWidth()/2-Config.WIDTH/2, Config.HEIGHT/2);
		player = new Player();
		Gdx.input.setInputProcessor(player.PIM);
		bg = new Sprite(new Texture(Resources.BANNER));
		bg.setPosition(bg.getWidth()/2-Config.WIDTH/2, Config.HEIGHT/2);
	}

	@Override
	public void show() {
		b = ClientRender.batch;
		
	}

	@Override
	public void render(float delta) {
		mouseInputs();
		time+= delta;
		ClientRender.cleanScreen();
		b.begin();
		bg.draw(b);
		menuBt.draw();
		letter.draw();
		b.end();
		
	}
	private void mouseInputs() {
		hoverButtons();
		if (player.PIM.isClick() && time > 0.2) {
			time = 0.0f;
			menuBt.update(player.PIM.getMouseX(), player.PIM.getMouseY());
		}
	}
	private void hoverButtons() {
		menuBt.updateHover(player.PIM.getMouseX(), player.PIM.getMouseY());
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
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
