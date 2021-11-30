package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ClientsideSprite extends Sprite {
	//Yes. We made an entire class just to add an int, because Marconi.
	private int id;
	String textureRoute;
	private boolean removed;
	
	public ClientsideSprite(String textureRoute) {
		super(new Texture(textureRoute));
		this.textureRoute = textureRoute;
		
	}
	public ClientsideSprite() {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	public int getID() {
		return id;
	}
	public String getRoute() {
		return textureRoute;
	}
	
	
}
