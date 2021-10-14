package tiledObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Road extends InteractiveObject {
	
	public Road( TiledMap map, Rectangle limites) {
		super( map, limites);
		//crea su caja 2d
		fixture.setUserData(this);
		
	}


	@Override
	public void interaction() {
		System.out.println("Escalable: colision");
		
		
	}

}
