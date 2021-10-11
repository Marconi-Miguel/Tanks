package utilities;

import java.util.Random;

public abstract class Resources {
	//lo dejo como ejemplo
	public static final String FUENTEMENU= "fuentes/BlackCloverFont.ttf";
	public static final String LOGO= "Logo OF.jpg";
	public static final String MENU= "fondo.png";
	public static final String MAPA1= "mapas/mapa1/mapaof.tmx";
	public static final String P1ATLAS= "pj1 movs/Pj1Sprites.atlas";
	public static final String CUADROATLAS ="cuadroTexto/cuadroTexto.atlas";
	public static final String JSONSTAGE = "Stage/Stage.json";
	//recursos de las habilidades
	public static final String RAYO = "habilidades/Rayo/Rayo.atlas";
	// toma desde el min hasta el max ambos incluidos
	public static int getRandom(int min,int max) {
		Random r = new Random();
		int res = r.nextInt(max+1-min)+min;
		return res;
	}
}
