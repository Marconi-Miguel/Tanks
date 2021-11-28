package TankData;

import elements.Explosion;
import elements.Hull;
import elements.Projectile;
import utilities.Render;
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
	
	@Override
	public void gotHitted(Hull hull) {
		super.gotHitted(hull);
		Explosion explosion = new Explosion(getX(),getY());
		Render.addSprite(explosion);
	}
	
}
