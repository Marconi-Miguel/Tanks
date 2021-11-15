package elements;

import TankData.BasicCannon;
import input.InputKeys;
import input.Player;
import utilities.Render;

public class Tank {
	public Hull hull;
	Player owner;
	float rotation;
	float maxSpeed;
	float rotationSpeed;
	float accelRate;
	float speed;
	float acceleration;
	boolean forward; // direction. true if going forward, false if reverse.
	float time;

	

	// Array holding other elements of the tank, such as the cannon.
	Attachable[] objects;

	public Tank(Hull hull, int x, int y, Player player) {
		//TODO SETEAR LA POSICION DESDE ACA
		owner = player;
		this.hull = hull;
		rotationSpeed = 1;
		
		objects = new Attachable[hull.slots];
		System.out.println(hull.slots);
		attach(new BasicCannon());
		
	}

	public void Render() {
		doMovement();
		doCannon();
		
		
		hull.draw(Render.batch); // this is the original way to draw the tank.
		
		updateObjects();// Update other sprites attached to this tank, such as cannon.
		// System.out.println("ACCEL: "+acceleration+" | SPEED: "+speed);
	}


	public void setPosition(float x, float y) {
		hull.setPosition(x, y);
	}

	//// movement functions
	private void doMovement() {
		doRotation();
		
		float tempX = (float) Math.sin(Math.toRadians(rotation));
		float tempY = (float) Math.cos(Math.toRadians(rotation));

		if (owner.inputs.get(InputKeys.UP) && !owner.inputs.get(InputKeys.DOWN)) { // If pressing W, go forward.
			
			tempX = (hull.isOnRoad() == true) ? tempX : tempX / 2;// here controls on road speed
			tempY = (hull.isOnRoad() == true) ? tempY : tempY / 2;//
			hull.moveHull(-tempX, tempY);
		} else if (owner.inputs.get(InputKeys.DOWN) && !owner.inputs.get(InputKeys.UP)) { // If pressing S, go reverse

			tempX = (hull.isOnRoad() == true) ? tempX / 1.5f : tempX / 3;// here controls on road speed
			tempY = (hull.isOnRoad() == true) ? tempY / 1.5f : tempY / 3;//
			hull.moveHull(tempX, -tempY);
		} else {
			hull.stopHull();
		}
		
		setPosition((hull.b2body.getPosition().x - hull.getWidth() / 2),// before this, doMovement only sets the body2d (rectangle)
				hull.b2body.getPosition().y - hull.getHeight() / 2); // and here updates the sprite
		

	}

	private void doRotation() {
		if (owner.inputs.get(InputKeys.RIGHT)) {
			rotate(rotationSpeed * -1);
		}

		if (owner.inputs.get(InputKeys.LEFT)) {
			rotate(rotationSpeed);
		}
	}

	public void rotate(float degrees) {
		hull.rotate(degrees);
		
		rotation += degrees;
		if (rotation >= 360) {
			rotation = 0;
		} else if (rotation < 0) {
			rotation = 359;
		}
	}

	///////////// Cannon-related functions.

	void doCannon() {
		if (owner.inputs.get(InputKeys.FIRE)) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i].objectType == "Cannon") {
					Cannon cannon = (Cannon) objects[i] ; // omg 
					cannon.trigger();
					
//					(Cannon) objects[i].trigger();
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
			
			object.tank = this;
		} // If a place was found, attach the object.
	}

	void updateObjects() { // Fix attached objects position & rotation
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] != null) {
				objects[i].update(hull.getX() +  hull.getWidth() / 2 - objects[i].getWidth()/2 , hull.getY() +  hull.getHeight() / 2, hull.getRotation());
									//TODO SETEAR BIEN ESTO, EL WITDH LE SUMO  LA MITAD DEL OBJETO PARA ESTAR EN EL CENTRO, PERO SI HAY MAS ATTACHABLES
									// NO DEBERIA SER ASI																												
			}
		}
		
	}

}
