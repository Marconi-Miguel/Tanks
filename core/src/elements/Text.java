package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import utilities.Render;;


public class Text {
	
	BitmapFont font;
	private int x=0,y=0;
	private String text="";
	GlyphLayout layout;
	public Text(int size, Color color, String fontRoute) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontRoute));
		FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameters.size = size;
		parameters.color = color;
		font = generator.generateFont(parameters);
		layout = new GlyphLayout();
	}

	public void draw() {
		font.draw(Render.batch, text, x, y);
		
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}
	
	
	public void setText(String text) {
		this.text = text;
		layout.setText(font, text);
	}
	
	public float getWidth() {
		return layout.width;
	}
	
	public float getHeight() {
		return layout.height;
	}	
}
