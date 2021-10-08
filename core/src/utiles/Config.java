package utiles;

public abstract class Config {
	public static int alto = 720;
    public static int ancho = 1080;
    // PIXELPERMETER la escala, un pixel a la imagen, para que las velocidades y otras propiedades tengan sentido
    public static final float PPM = 100;
    
    // filtros utilizando BITWISE:
    public static final short DEFAULT_BIT = 1;
    public static final short ENTIDAD_BIT = 2;
    public static final short COFRE_BIT = 4;
    public static final short SALIDA_BIT = 8;
    public static final short ABIERTO_BIT = 16;
    public static final short PJ_BIT = 32;
    public static final short ESCALABLE_BIT = 64;
    public static final short LIBRE_BIT = 128;
    // 0000 1100
}
