package elements;

import TankData.BasicCannon;
import TankData.BasicHull;
import input.Client;
import input.InputKeys;
import input.Player;
import utilities.Config;
import utilities.Render;

public class Tank {
	public Hull hull;
	public Client owner;
	boolean forward; // direction. true if going forward, false if reverse.
	float time;
	public float tempX = 0;
	public float tempY = 0;
	public float correctionX,correctionY;
	
	public boolean correction;
	// Array holding other elements of the tank, such as the cannon.
	Attachable[] objects;

	public Tank(Client player) {
		// TODO SETEAR LA POSICION DESDE ACA
		owner = player;
		this.hull = new BasicHull(this);
		objects = new Attachable[hull.slots];
		// for now, the tanks only will use 1 cannon
		attach(new BasicCannon());

	}

	public void Render() {
		time += Config.delta;
		if (!correction) {
			doMovement();
		}else {
			doCorrectionMovement();
		}

		doCannon();
		hull.draw(Render.batch); // this is the original way to draw the tank.

		updateObjects();// Update other sprites attached to this tank, such as cannon.
		// System.out.println("ACCEL: "+acceleration+" | SPEED: "+speed);
	}

	private void doCorrectionMovement() {
		float difX = hull.getX() - correctionX;
		float difY = hull.getY() - correctionY;
		
		if(difX!=0) {
			if(difX<0) {
				hull.moveHull(-1,0);
			}else {
				hull.moveHull(1, 0);
			}
		}else if(difY != 0) {
			if(difY<0) {
				hull.moveHull(0,-1);
			}else {
				hull.moveHull(0, 1);
			}
		}else if(difX==0 && difY==0){
			correction = false;
		}
		
	}

	public void setPosition(float x, float y) {
		hull.setPosition(x, y);
	}

	public void setRotation(float rotation) {
		hull.rotation = rotation;
		hull.setRotation(rotation);
	}

	//// movement functions
	private void doMovement() {
		doRotation();

		tempX = (float) Math.sin(Math.toRadians(hull.rotation));
		tempY = (float) Math.cos(Math.toRadians(hull.rotation));
		if (hull.roadCounter == 0) {
			tempX = tempX / 2;
			tempY = tempY / 2;
		}
		if (hull.isBuffSpeed()) {
			tempX = tempX * 1.4f;
			tempY = tempY * 1.4f;
		}

//		hull.
		if (owner.inputs.get(InputKeys.UP) && !owner.inputs.get(InputKeys.DOWN)) { // If pressing W, go forward.

			hull.moveHull(-tempX, tempY);
		} else if (owner.inputs.get(InputKeys.DOWN) && !owner.inputs.get(InputKeys.UP)) { // If pressing S, go reverse
			hull.moveHull(tempX / 1.5f, -tempY / 1.5f);
		} else {
			hull.stopHull();
		}

		setPosition((hull.b2body.getPosition().x - hull.getWidth() / 2), // before this, doMovement only sets the
				hull.b2body.getPosition().y - hull.getHeight() / 2); // body2d(the rectangle) and here updates the
																		// sprite

	}

	private void doRotation() {
		if (owner.inputs.get(InputKeys.RIGHT)) {
			rotate(hull.rotationSpeed * -1);
		}

		if (owner.inputs.get(InputKeys.LEFT)) {
			rotate(hull.rotationSpeed);
		}
	}

	public void rotate(float degrees) {
		hull.rotate(degrees);

		hull.rotation += degrees;
		if (hull.rotation >= 360) {
			hull.rotation = 0;
		} else if (hull.rotation < 0) {
			hull.rotation = 359;
		}
	}

	///////////// Cannon-related functions.

	private void doCannon() {
		if (owner.inputs.get(InputKeys.FIRE)) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i].objectType == "Cannon") {// failsafe from
					if (time > ((Cannon) objects[i]).reloadTime) {// spamming space
						time = 0;
						((Cannon) objects[i]).trigger(); // omg
					}
				}
			}
		}
	}

	///////////// functions related to attached objects
	public void attach(Attachable object) {
		int availablePos = -1;
		for (int i = 0; i < objects.length; i++) { // Find an available position in the array.
			if (availablePos == -1 && objects[i] == null) {
				availablePos = i;
			}
		}
		if (availablePos != -1) {
			objects[availablePos] = object;

			object.hull = this.hull;
		} // If a place was found, attach the object.
	}

	void updateObjects() { // Fix attached objects position & rotation
		//
		for (int i = 0; i < objects.length; i++) {
			if (objects[i].objectType.equals("Cannon")) { // updates cannon a its respective projectiles
				((Cannon) objects[i]).updateCannon();
			}
			if (objects[i] != null) {
				objects[i].update(hull.getX() + hull.getWidth() / 2 - objects[i].getWidth() / 2,
						hull.getY() + hull.getHeight() / 2, hull.getRotation());
				// TODO look a this, when added more attachables, cause its only tought to have
				// 1 attachable

			}

		}

	}

	public void destroy() {
		hull.disappear();

	}

	public void correction(float x, float y) {
		correctionX = x;
		correctionY = y;
	}

}
