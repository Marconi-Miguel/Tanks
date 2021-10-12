package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import utilities.Render;

public class ImageClass {
	private Texture t;
	public Sprite s;
	public ImageClass(String ruta) {
		t= new Texture(ruta);
		s= new Sprite(t);
	}
	
	public ImageClass(TextureRegion region) {
		s= new Sprite(region);
	}

	public void draw() {
		s.draw(Render.batch);
	}

	public void setTransparencia (float a){
		s.setAlpha(a);
	}
	public void setSize(float ancho, float alto) {
		s.setSize(ancho,alto);
		
	}
	public float getAlto(){
		return s.getHeight();
	}
	public float getAncho(){
		return s.getWidth();
	}
	public float getOriginX(){
		return s.getX();
	}
	public float getOriginY(){
		return s.getY();
	}
	public void setPosition(float x, float y) {
		s.setPosition(x, y);
	}
	
	
}
