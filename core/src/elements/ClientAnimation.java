package elements;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ClientAnimation extends Sprite {
	private int id;
	private float x, y;

	public ClientAnimation() {

	}

	public void update() {
		
	}
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
