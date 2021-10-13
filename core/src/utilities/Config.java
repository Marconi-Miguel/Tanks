package utilities;

public abstract class Config {
	public static int HEIGHT= 720;
    public static int WIDTH = 1080;
    // PIXELPERMETER la escala, un pixel a la imagen, para que las velocidades y otras propiedades tengan sentido
    public static final float PPM = 100;
    
    // filtros utilizando BITWISE:
    public static final short DEFAULT_BIT = 1;
    public static final short BARRIL_BIT = 2;
    public static final short BOLSA_BIT = 4;
    public static final short SALIDA_BIT = 8;
    
    // 0000 1100
}
