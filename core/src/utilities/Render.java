package utilities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.bws.tanks.Tanks;

import elements.ClientSprite;
import input.Player;

public abstract class Render {
	public static SpriteBatch batch;
	public static Tanks app;
	public static World world;
	public static Player player;
	public static ArrayList<ClientSprite> renderList = new ArrayList<ClientSprite>();
	public static ArrayList<Sprite> renderAnimationList = new ArrayList<Sprite>();

	int renderID;

	public static void render() { // Render everything in the renderList
		Render.batch.begin();
		for (int i = 0; i < renderList.size(); i++) {
			if (renderList.get(i) != null) {
				renderList.get(i).draw(batch);
			}
		}
		Render.batch.end();
	}
	public static void renderAnimations() { // Render everything in the renderList
		Render.batch.begin();
		for (int i = 0; i < renderList.size(); i++) {
			if (renderList.get(i) != null) {
				renderList.get(i).draw(batch);
			}
		}
		Render.batch.end();
	}

	public static void disposeList() {
		for (int i = 0; i < renderList.size(); i++) {
			if (renderList.get(i) != null) {
				renderList.get(i).getTexture().dispose();
				;
			} else {
				renderList.remove(i);
			}
		}
	}

	public static void cleanScreen() {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public static void addSprite(ClientSprite sprite) {
		renderList.add(sprite);
	}

}
