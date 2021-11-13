package tiledObjects;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import TankData.Hull;
import elements.Tank;

public class WorldListener implements ContactListener {
	private Fixture fixA;
	private Fixture fixB;
	public Fixture actual;
	private int roadCounter; // im TOOOOOOOOO LAZY 
	//basically it will count how many roads the tanks has been touching, when he left the road discounts 1
	// when the hull left a road it will look if the roadCounter is 0, else it wont trigger "hull.outRoad"

	@Override
	public void beginContact(Contact contact) {
		
		// el contacto genera entre 2 entidades, pero puede chocar mas de una entidad,
		// se generarian 2 BEGINcontact
		fixA = contact.getFixtureA();
		fixB = contact.getFixtureB();
		
		if (fixA.getUserData().equals("Road") || fixB.getUserData().equals("Road")) {

			Fixture Road = (fixA.getUserData().equals("Road")) ? fixA : fixB;
			Fixture objeto = (Road == fixA) ? fixB : fixA;
			
			if (objeto.getUserData() != null && (objeto.getUserData() instanceof Hull)) {
				roadCounter +=1;
				actual = objeto;
				// se activa la interaccion con el tipo de objeto que sea
				((Hull) objeto.getUserData()).inRoad();
				
			}

		}

	}

	// -------------------------END CONTACT------------------------------------

	@Override
	public void endContact(Contact contact) {
		
		

		if (fixA.getUserData().equals("Road") || fixB.getUserData().equals("Road")) {

			Fixture Road = (fixA.getUserData().equals("Road")) ? fixA : fixB;
			Fixture objeto = (Road == fixA) ? fixB : fixA;
			
			if (objeto.getUserData() != null && (objeto.getUserData() instanceof Hull)) {
				roadCounter-=1;
				
				// se activa la interaccion con el tipo de objeto que sea
				if(roadCounter==0) {
					
					((Hull) objeto.getUserData()).outRoad();
				}
			}

		}

	}

	public Fixture getActual() {
		return actual;
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
