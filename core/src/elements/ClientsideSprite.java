package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ClientsideSprite extends Sprite {
	//Yes. We made an entire class just to add an int, because Marconi.
	int id;
	String textureRoute;
	
	public ClientsideSprite(Texture texture) {
		super(texture);
		
	}
	public ClientsideSprite() {
		
	}
	
	public void setID(int id) {
		this.id = id;
	}
	public int getID() {
		return id;
	}
	
	
}
