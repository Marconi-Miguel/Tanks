package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageClass {
	
	Texture texture;
	public Sprite sprite;

	public void Render(final SpriteBatch batch) {
		
		sprite.draw(batch);
	}
	
}
