package tiledObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import utiles.Config;

public class Escalable extends InteractiveObject {
	
	public Escalable( TiledMap map, Rectangle limites) {
		super( map, limites);
		//crea su caja 2d
		fixture.setUserData(this);
		definirFiltro(Config.ESCALABLE_BIT);
	}

	@Override
	public void interaction() {
		System.out.println("Escalable: colision");
		
		
	}

}
