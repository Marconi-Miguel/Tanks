package listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldListener implements ContactListener {
	private Fixture fixA;
	private Fixture fixB;
	public Fixture actual;

	@Override
	public void beginContact(Contact contact) {
		
		// el contacto genera entre 2 entidades, pero puede chocar mas de una entidad,
		// se generarian 2 BEGINcontact
		fixA = contact.getFixtureA();
		fixB = contact.getFixtureB();
		
		if (fixA.getUserData().equals("projectile") || fixB.getUserData().equals("projectile")) {
			
//			Fixture projectile = (fixA.getUserData().equals("projectil")) ? fixA : fixB;
//			Fixture objeto = ("projectil" == fixA) ? fixB : fixA;
//			
//			if (objeto.getUserData() != null && (objeto.getUserData() instanceof barril)) {
//				
//				actual = objeto;
//				// se activa la interaccion con el tipo de objeto que sea
//				((ObjetoInteractivo) objeto.getUserData()).explotar();
//			}
			
			
			
			
			
			
			

			// se continua con las 3 direcciones restantes
			////
		} 
		
	}
	
	
	
	
	
	
	
	// -------------------------FINALIZA CONTACTO------------------------------------
	
	
	
	
	
	
	
	
	

	@Override
	public void endContact(Contact contact) {
		
		
	}
	public Fixture getActual() {
		return actual;
	}
	public boolean isContacto() {
		if(actual == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}
	

}
