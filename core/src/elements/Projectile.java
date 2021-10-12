package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projectile extends ImageClass{
	
	
	public float rotation;
	public float speed;
	//TODO register parent tank
	

	public Projectile(float x, float y, float rotation, String texture, float speed){
		super(texture);
		
		this.rotation += rotation;
		s.setX(x);
		s.setY(y);
		this.speed = speed;
	}
	
	public void draw() {
		super.draw();
		doMovement();
	}
	
	
	
	void doMovement() {
		float tempX = (float) Math.cos(Math.toRadians(rotation) ) * speed;
		float tempY = (float) Math.sin(Math.toRadians(rotation) ) * speed;
		setPosition(s.getX() + tempX * -1, s.getY() + tempY * -1);
		s.setRotation(rotation+90);
	}
	
}
