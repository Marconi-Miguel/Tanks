package listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import objetostiled.Cofre;
import objetostiled.ObjetoInteractivo;

public class ListenerMundo implements ContactListener {
	private Fixture fixA;
	private Fixture fixB;
	public Fixture actual;

	@Override
	public void beginContact(Contact contact) {
		
		// el contacto genera entre 2 entidades, pero puede chocar mas de una entidad,
		// se generarian 2 BEGINcontact
		fixA = contact.getFixtureA();
		fixB = contact.getFixtureB();
		
		if (fixA.getUserData().equals("arriba") || fixB.getUserData().equals("arriba")) {
			
			Fixture arriba = (fixA.getUserData().equals("arriba")) ? fixA : fixB;
			Fixture objeto = (arriba == fixA) ? fixB : fixA;
			
			if (objeto.getUserData() != null && (objeto.getUserData() instanceof ObjetoInteractivo)) {
				
				actual = objeto;
				// se activa la interaccion con el tipo de objeto que sea
				((ObjetoInteractivo) objeto.getUserData()).interaccion();
			}
			if(objeto.getUserData() instanceof Cofre) {
				((Cofre)objeto.getUserData()).setContactoFrente(true);
			}
			
			
			
			
			
			

			// se continua con las 3 direcciones restantes
			////
		} 
		
	}
	
	
	
	
	
	
	
	// -------------------------FINALIZA CONTACTO------------------------------------
	
	
	
	
	
	
	
	
	

	@Override
	public void endContact(Contact contact) {
		
		fixA = contact.getFixtureA();
		fixB = contact.getFixtureB();
		if (fixA.getUserData().equals("arriba") || fixB.getUserData().equals("arriba")) {

			Fixture arriba = (fixA.getUserData().equals("arriba")) ? fixA : fixB;
			Fixture objeto = (arriba == fixA) ? fixB : fixA;
			if (objeto.getUserData() != null && (objeto.getUserData() instanceof ObjetoInteractivo)) {
				// se activa la interaccion con el tipo de objeto que sea
				((ObjetoInteractivo) objeto.getUserData()).interaccion();
			}
			if(objeto.getUserData() instanceof Cofre) {
				((Cofre)objeto.getUserData()).setContactoFrente(false);
			}

			// se continua con las 3 direcciones restantes
		} else if (fixA.getUserData().equals("abajo") || fixB.getUserData().equals("abajo")) {
			Fixture abajo = (fixA.getUserData().equals("abajo")) ? fixA : fixB;
			Fixture objeto = (abajo == fixA) ? fixB : fixA;
			if (objeto.getUserData() != null && (objeto.getUserData() instanceof ObjetoInteractivo)) {
				System.out.print("abajo ");
				((ObjetoInteractivo) objeto.getUserData()).interaccion();
			}
		} else if (fixA.getUserData().equals("izquierda") || fixB.getUserData().equals("izquierda")) {
			Fixture izquierda = (fixA.getUserData().equals("izquierda")) ? fixA : fixB;
			Fixture objeto = (izquierda == fixA) ? fixB : fixA;
			if (objeto.getUserData() != null && (objeto.getUserData() instanceof ObjetoInteractivo)) {
				System.out.print("izquierda ");
				((ObjetoInteractivo) objeto.getUserData()).interaccion();
			}
		} else if (fixA.getUserData().equals("derecha") || fixB.getUserData().equals("derecha")) {
			Fixture derecha = (fixA.getUserData().equals("derecha")) ? fixA : fixB;
			Fixture objeto = (derecha == fixA) ? fixB : fixA;
			if (objeto.getUserData() != null && (objeto.getUserData() instanceof ObjetoInteractivo)) {
				System.out.print("derecha ");
				((ObjetoInteractivo) objeto.getUserData()).interaccion();
			}
		}
		
		
		actual = null;

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
