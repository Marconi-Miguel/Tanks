package tiledObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class Camino extends InteractiveObject {
	
	public Camino( TiledMap map, Rectangle limites) {
		super( map, limites);
		//crea su caja 2d
		fixture.setUserData(this);
		
	}

	@Override
	public void interaction() {
		System.out.println("Escalable: colision");
		
		
	}

}
