package utilities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.bws.tanks.Tanks;

import elements.ClientAnimation;
import elements.ClientsideSprite;
import input.Player;

public abstract class ClientRender {
	public static SpriteBatch batch;
	public static Tanks app;
	public static World world;

	public static ArrayList<ClientsideSprite> renderList = new ArrayList<ClientsideSprite>();
	public static ArrayList<ClientAnimation> renderAnimationList = new ArrayList<ClientAnimation>();
	public static int aux;

	int renderID;

	public static void render() { // Render everything in the renderList
		batch.begin();
		for (int i = 0; i < renderList.size(); i++) {
			if (renderList.get(i) != null) {
				renderList.get(i).draw(batch);
				if (renderList.get(i).isRemoved()) {
					renderList.remove(i);
				}
			}
		}
		for (int i = 0; i < renderAnimationList.size(); i++) {
			if (renderAnimationList.get(i) != null) {
				renderAnimationList.get(i).draw(batch);
				renderAnimationList.get(i).update();
				if (renderAnimationList.get(i).end) {
					renderAnimationList.remove(i);
				}

			}
		}
		batch.end();
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
		try {
			ClientsideSprite sprite = new ClientsideSprite(args[0]);
			sprite.setID(Integer.parseInt(args[1]));
			sprite.setX(Float.parseFloat(args[2]));
			sprite.setY(Float.parseFloat(args[3]));
			sprite.setSize(Float.parseFloat(args[5]), Float.parseFloat(args[6]));
			sprite.setOrigin(Float.parseFloat(args[7]), Float.parseFloat(args[8]));
			sprite.setRotation(Float.parseFloat(args[4]));
			renderList.add(sprite);
		} catch (Exception e) {

		}

	}

	public static void addAnimation(String[] args) {

		try {
			float correctX, correctY;
			System.out.println(args);
			correctX = Float.parseFloat(args[0]);
			correctY = Float.parseFloat(args[1]);
			correctX = (correctX == 0)?-0.5f:correctX;
			correctY = (correctY == 0)?-0.5f:correctX;
			ClientAnimation animation = new ClientAnimation(correctX, correctY);
			renderAnimationList.add(animation);
		} catch (NumberFormatException e) {

		}

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

			try {
//				System.out.println(args[0]);
				sprite.setX(Float.parseFloat(args[2]));
				sprite.setY(Float.parseFloat(args[3]));
				if (!args[4].equals("")) {
					sprite.setRotation(Float.parseFloat(args[4]));
				}
			} catch (NumberFormatException e) {
			}

		} else { // CONSIDER REMOVING THIS ELSE?
			addSprite(args);
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
