package TankData;

import elements.Hull;
import elements.Projectile;

public class BasicShell extends Projectile{
	public BasicShell(float x, float y, Hull hull, String texture, float speed) {
		super(x, y, hull, texture, speed);
		dmg = 100;
	}

	
}
