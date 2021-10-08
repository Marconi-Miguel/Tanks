package utiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Imagen {
	private Texture t;
	public Sprite s;
	public Imagen(String ruta) {
		t= new Texture(ruta);
		s= new Sprite(t);
	}
	public Imagen(TextureRegion region) {
		s= new Sprite(region);
	}
	public void dibujar() {
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
	public void setPosition(float x, float y) {
		s.setPosition(x, y);
	}
	
}