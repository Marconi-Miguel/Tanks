package utiles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import objetostiled.Cofre;
import objetostiled.Escalable;
import objetostiled.Paredes;
import objetostiled.Roca;
import objetostiled.Salida;

public class Mundo2D {

	public Mundo2D(World mundo, TiledMap mapa) {
		// abajo se crearan objetos Interactivos
		// Salidas // for each que saca cada cuadro creado por el tiledSoftware y le asigna un nuevo objeto
		//         // el numero que cambia es el numero de la capa de la que se quiere sacar los cuadros
		
		// paredes
		for (MapObject object : mapa.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Paredes(mundo, mapa, rect);
			
		}
		//salida
		for (MapObject object : mapa.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
			//saca el rectangulo
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			// al nuevo objeto se le asigna que propiedades tendra, en este caso salida
			new Salida(mundo, mapa, rect);
		}
		// Cofres
		for (MapObject object : mapa.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Cofre(mundo, mapa, rect);
		}
		// Escalable

		for (MapObject object : mapa.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Escalable(mundo, mapa, rect);
		}
		//rocas
		for (MapObject object : mapa.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Roca(mundo, mapa, rect);
		}
	}
}
