package utilities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.bws.tanks.Tanks;

import elements.ClientsideSprite;
import input.Player;

public abstract class Render {
	public static SpriteBatch batch;
	public static Tanks app;
	public static World world;
	public static Player player;
	public static ArrayList<ClientsideSprite> renderList = new ArrayList<ClientsideSprite>();

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

	public static void addSprite(String[] args) {
		ClientsideSprite sprite = new ClientsideSprite(new Texture(args[0]));
		sprite.setID(Integer.parseInt(args[1]));
		sprite.setX(Float.parseFloat(args[2]));
		sprite.setY(Float.parseFloat(args[3]));
		sprite.setRotation(Float.parseFloat(args[4]));
		sprite.setRotation(Float.parseFloat(args[4]));
		sprite.setSize(Float.parseFloat(args[5]),Float.parseFloat(args[6]));
		renderList.add(sprite);
	}

	public static void updateSprite(String[] args) {
		ClientsideSprite sprite = null;
		for (int i = 0; i < renderList.size(); i++) {
			if (renderList.get(i).getID() == Integer.parseInt(args[1])) {
				sprite = renderList.get(i);
				break;
			}
		}
		if (sprite != null) {
			sprite.setX(Float.parseFloat(args[2]));
			sprite.setY(Float.parseFloat(args[3]));
			sprite.setRotation(Float.parseFloat(args[4]));
			sprite.setSize(Float.parseFloat(args[5]),Float.parseFloat(args[6]));
		}
	}

	public static void removeSprite(int ID) {
		for (int i = 0; i < renderList.size(); i++) {
			if (renderList.get(i).getID() == ID) {
				renderList.remove(i);
				break;
			}
		}
	}

}
