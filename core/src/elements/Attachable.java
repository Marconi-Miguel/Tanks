package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import utilities.Render;

public class Attachable extends Sprite{
	

	Tank tank;
	public String objectType; 
	
	
	public Attachable(String texture) {
		super(new Texture(texture));	
		
	}
	
	public void update( float x, float y, float originX, float originY,float Rotation) {
		setX(x); // TODO: Setter and getter for this on the Resources.java
		setY(y);
		setOrigin(originX, originY);
		setRotation(Rotation);
		draw(Render.batch);
	}
	
	public void modifyTexture(Texture texture) {
		//this.texture.dispose(); //Gets rid of the old texture.
		setTexture(texture);

	}


}
