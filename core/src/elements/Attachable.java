package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import utilities.Config;
import utilities.Render;

public class Attachable extends Sprite{
	

	Tank tank;
	public String objectType; 
	
	
	public Attachable(String texture) {
		super(new Texture(texture));
		setSize(getWidth()/2/Config.PPM,getHeight()/2/Config.PPM);
		setOrigin(getWidth()/2, 0);
		
	}
	
	public void update( float x, float y,float Rotation) {
		setX(x); // TODO: Setter and getter for this on the Resources.java
		setY(y);
		
		setRotation(Rotation);
		draw(Render.batch);
	}
	
	public void modifyTexture(Texture texture) {
		//this.texture.dispose(); //Gets rid of the old texture.
		setTexture(texture);

	}


}
