package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projectile extends ImageClass{
	
	Texture texture;
	Sprite sprite;
	public float rotation;
	public float speed;
	

	public Projectile(float x, float y, float rotation, String texture, float speed){
		this.texture = new Texture(texture);	
		sprite = new Sprite(this.texture);
		this.rotation += rotation;
		sprite.setX(x);
		sprite.setY(y);
		this.speed = speed;
	}
	
	public void Render(SpriteBatch batch) {
		doMovement();
		sprite.draw(batch);
	}
	
	void setPosition(float x, float y) {
		sprite.setX(x);
		sprite.setY(y);
	}
	
	void doMovement() {
		float tempX = (float) Math.cos(Math.toRadians(rotation) ) * speed;
		float tempY = (float) Math.sin(Math.toRadians(rotation) ) * speed;
		setPosition(sprite.getX() + tempX * -1, sprite.getY() + tempY * -1);
		sprite.setRotation(rotation+90);
	}
	
}
