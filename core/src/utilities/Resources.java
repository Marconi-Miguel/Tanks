package utilities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resources {
	private TextureAtlas atlas;
	//GAME ICON
	public static final String GAMEICON = "images/misc/icon.png";
	
	//MAPS
	public static final String MAP1= "Maps/mapaDessert.tmx";
	//HULLS /////////////////////////////////////////
	public static final String BASICHULLBLUE = "Tanks/Blue/tankBody_blue_outline.png";
	public static final String BASICHULLDARK= "Tanks/Blue/tankBody_blue_outline.png";
	public static final String BASICHULLGREEN = "Tanks/Blue/tankBody_blue_outline.png";
	public static final String BASICHULLRED= "Tanks/Blue/tankBody_blue_outline.png";
	public static final String BASICHULLSAND = "Tanks/Blue/tankBody_blue_outline.png";
	
	//CANNONS /////////////////////////////////////////
	public static final String BASICCANNON = "images/cannons/basic.png";
	public static final String BASICCANNON_FIRE = "images/cannons/basic_fire.png";
	
	//CANNON SFX//////////////////////////////////////
	public static final String BASICCANNON_FIRE_SFX = "audio/sfx/cannon/basic_fire.mp3";
	
	//SHELLS ////////////////////////////////////////
	public static final String BASICSHELL = "images/shells/basic.png";

	
//	public static TextureRegion getTextureRegion(int color, String ruta) {
//		TextureRegion region;
//		switch (color) {
//		case 1:
//			atlas = new TextureAtlas(BOTONESAZUL);
//			region = atlas.findRegion(ruta);
//			return region;
//		case 2:
//
//			atlas = new TextureAtlas(BOTONESROJO);
//			region = atlas.findRegion(ruta);
//			return region;
//		case 3:
//			atlas = new TextureAtlas(BOTONESVERDE);
//			region = atlas.findRegion(ruta);
//			return region;
//		case 4:
//			atlas = new TextureAtlas(BOTONESGRIS);
//			region = atlas.findRegion(ruta);
//			return region;
//		default:
//			return null;
//		}
//	}
}
