package tiledMapObjects;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import elements.CooldownBuff;
import elements.ExplosiveBuff;
import elements.Hull;
import elements.Projectile;
import elements.SpeedBuff;

public class WorldListener implements ContactListener {
	private Fixture fixA;
	private Fixture fixB;
	public Fixture actualLeft;
	public Fixture actualIn;

	@Override
	public void beginContact(Contact contact) {
		System.out.println("asd");
		// el contacto genera entre 2 entidades, pero puede chocar mas de una entidad,
		// se generarian 2 BEGINcontact
		fixA = contact.getFixtureA();
		fixB = contact.getFixtureB();
		detectRoad();
		detectProjectil();
		detectBuff();

	}

	private void detectBuff() {
		if (fixA.getUserData() instanceof ExplosiveBuff || fixB.getUserData() instanceof ExplosiveBuff ) {

			Fixture explosive = (fixA.getUserData() instanceof ExplosiveBuff) ? fixA : fixB;
			Fixture objeto = (explosive == fixA) ? fixB : fixA;

			if (objeto.getUserData() != null && (objeto.getUserData() instanceof Hull)) {
				// se activa la interaccion con el tipo de objeto que sea
					((ExplosiveBuff) explosive.getUserData()).pick();
			}

		} else if (fixA.getUserData() instanceof SpeedBuff || fixB.getUserData() instanceof SpeedBuff ) {

			Fixture speed = (fixA.getUserData() instanceof SpeedBuff ) ? fixA : fixB;
			Fixture objeto = (speed == fixA) ? fixB : fixA;

			if (objeto.getUserData() != null && (objeto.getUserData() instanceof Hull)) {

				// se activa la interaccion con el tipo de objeto que sea
//				((Hull) objeto.getUserData()).inRoad();

			}

		} else if (fixA.getUserData() instanceof CooldownBuff  || fixB.getUserData() instanceof CooldownBuff ) {

			Fixture cooldown = (fixA.getUserData() instanceof CooldownBuff ) ? fixA : fixB;
			Fixture objeto = (cooldown == fixA) ? fixB : fixA;

			if (objeto.getUserData() != null && (objeto.getUserData() instanceof Hull)) {

				// se activa la interaccion con el tipo de objeto que sea 
//				((Hull) objeto.getUserData()).inRoad();

			}

		}

	}

	private void detectProjectil() {
		if (fixA.getUserData() instanceof Projectile || fixB.getUserData() instanceof Projectile) {

			Fixture projectile = (fixA.getUserData() instanceof Projectile) ? fixA : fixB;
			Fixture hull = (projectile == fixA) ? fixB : fixA;

			if (hull.getUserData() != null && (hull.getUserData() instanceof Hull)) {
				// trigger sensors

				if (((Hull) hull.getUserData()) != ((Projectile) projectile.getUserData()).parent
						&& !((Projectile) projectile.getUserData()).isExploded()) {

					((Projectile) projectile.getUserData()).explode();
					((Hull) hull.getUserData()).receiveDamage(((Projectile) projectile.getUserData()));

				}

			}

		}
	}

	private void detectRoad() {
		if (fixA.getUserData().equals("Road") || fixB.getUserData().equals("Road")) {

			Fixture road = (fixA.getUserData().equals("Road")) ? fixA : fixB;
			Fixture objeto = (road == fixA) ? fixB : fixA;

			if (objeto.getUserData() != null && (objeto.getUserData() instanceof Hull)) {

				// se activa la interaccion con el tipo de objeto que sea
				if ((actualIn == null || actualIn != road) || ((Hull) objeto.getUserData()).roadCounter == 0) {
					((Hull) objeto.getUserData()).inRoad();
				}

				actualIn = road;

			}

		}

	}

	// -------------------------END CONTACT------------------------------------

	@Override
	public void endContact(Contact contact) {
		if (fixA.getUserData() != null && fixB.getUserData() != null) {
			if (fixA.getUserData().equals("Road") || fixB.getUserData().equals("Road")) {

				Fixture road = (fixA.getUserData().equals("Road")) ? fixA : fixB;
				Fixture objeto = (road == fixA) ? fixB : fixA;

				if (objeto.getUserData() != null && (objeto.getUserData() instanceof Hull)) {
					// se activa la interaccion con el tipo de objeto que sea
					// idk what happened really, but i thinks that the projectil counted as a hull(I
					// DONT KNOW WHY, SO I DID THIS TO NO REPEAT THE SAME ROAD unless
					// the tank reEnters that single road)
					if ((actualLeft == null || actualLeft != road) || ((Hull) objeto.getUserData()).roadCounter > 0) {
						((Hull) objeto.getUserData()).outRoad();

					}
					actualLeft = road;

				}

			}
		}

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
