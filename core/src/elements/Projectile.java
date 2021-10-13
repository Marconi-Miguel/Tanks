package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import utilities.Render;

public class Projectile extends Sprite{
	
	
	public float rotation;
	public float speed;
	//TODO register parent tank
	

	public Projectile(float x, float y, float rotation, String texture, float speed){
		super(new Texture(texture));
		
		this.rotation += rotation;
		setX(x);
		setY(y);
		this.speed = speed;
	}
	
	public void draw() {
		super.draw(Render.batch);
		doMovement();
	}
	
	
	
	void doMovement() {
		float tempX = (float) Math.cos(Math.toRadians(rotation) ) * speed;
		float tempY = (float) Math.sin(Math.toRadians(rotation) ) * speed;
		setPosition(getX() + tempX * -1, getY() + tempY * -1);
		setRotation(rotation+90);
	}
	
}
