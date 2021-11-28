package TankData;

import elements.Hull;
import elements.Projectile;
import utilities.Resources;

public class ExplosiveShell extends Projectile{
	float ExplosiveDmg;
	public ExplosiveShell(float x, float y, Hull hull) {
		super(x, y, hull, Resources.EXPLOSIVESHELL, 2);
		dmg = 50;
		ExplosiveDmg = 120;
	}
	public float getExplosiveDmg() {
		return ExplosiveDmg;
	}

	
}
