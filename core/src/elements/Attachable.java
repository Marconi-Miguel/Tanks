package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Attachable {
	
	Texture texture;
	Sprite sprite;
	Tank tank;
	public String objectType; 
	
	
	public Attachable(String texture) {
		this.texture = new Texture(texture);	
		sprite = new Sprite(this.texture);
	}
	
	public void update(SpriteBatch batch, float x, float y, float originX, float originY,float Rotation) {
		sprite.setX(x); // TODO: Setter and getter for this on the Resources.java
		sprite.setY(y);
		sprite.setOrigin(originX, originY);
		sprite.setRotation(Rotation);
		sprite.draw(batch);
	}
	
	public void modifyTexture(Texture texture) {
		//this.texture.dispose(); //Gets rid of the old texture.
		this.texture = texture;
		sprite.setTexture(this.texture);
	}


}
