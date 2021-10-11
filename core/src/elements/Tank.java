package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import TankData.Hull;
import input.VehicleController;

public class Tank extends ImageClass {
	
	public VehicleController controller;
	Texture texture;
	public Sprite sprite;
	
	float rotation;
	float maxSpeed;
	float rotationSpeed;
	float accelRate;
	float speed;
	float acceleration;
	boolean forward; //direction. true if going forward, false if reverse.
	
	//Array holding other elements of the tank, such as the cannon.
	Attachable[] objects;
	

	
	public Tank(Hull hull, VehicleController controller,int x, int y) {
		this.texture = hull.texture;
		sprite = new Sprite(this.texture);
		sprite.setX(x);
		sprite.setY(y);
		sprite.setOrigin(hull.originX,hull.originY);
		this.controller = controller;
		objects = new Attachable[hull.slots];
		
		maxSpeed = hull.maxSpeed;
		accelRate = hull.accelRate;
		rotationSpeed = hull.rotationSpeed;
		rotation = hull.startRotation;
	}
	
	public void Render(final SpriteBatch batch) {
		doMovement();
		doCannon();
		sprite.draw(batch);
		updateObjects(batch);//Update other sprites attached to this tank, such as cannon.
		//System.out.println("ACCEL: "+acceleration+" | SPEED: "+speed);
	}
	
	public void setPosition(float x, float y) {
		sprite.setX(x);
		sprite.setY(y);
	}
	
	public void rotate(float degrees) {	
		sprite.rotate(degrees);
		rotation += degrees;
		 if (rotation >= 360){
			 rotation = 0;
		 }else if (rotation < 0) {
			 rotation = 359;
		 }
	}
	
	
	////movement functions
	private void doMovement() {
		doSpeed();
		if(controller.right) {
			rotate(rotationSpeed * -1);
		}
		
		if(controller.left) {
			rotate(rotationSpeed);
			
		}
		
		float tempX = (float) Math.cos(Math.toRadians(rotation) ) * speed/40;
		float tempY = (float) Math.sin(Math.toRadians(rotation) ) * speed/40;
		setPosition(sprite.getX() + tempX * -1, sprite.getY() + tempY * -1);
	}
	
	private void doSpeed() {
		if (controller.up && !controller.down) { //If pressing W, go forward.
			accelerate(true);
		}else if (controller.down && !controller.up) { //If pressing S, go reverse
			accelerate(false);
		}else if (!checkStop() ){ //Slowly stop if not accelerating or ready to stop.
			slowDown();
		}
		speed += acceleration;
		if(speed > 0 && speed >= maxSpeed) {
			speed = maxSpeed;
		}else if (speed < 0 && speed <= (maxSpeed/2) * -1 ) {
			speed = (maxSpeed/2) * -1;
		}
		
	}
	
	void accelerate(boolean forward) {
		this.forward = forward;
		float modifier;
		if(forward) {modifier = 1; }else {modifier = -0.5f;} //Handle forward/reverse and it's modifier. (-50% speed on reverse)
		
		if( ( (speed > 0 && !forward) || (speed < 0 && forward) ) && !checkStop() ) { //If changing directions.
			slowDown();
		}else if(speed > maxSpeed/5) {acceleration = accelRate * modifier;} //If it's up to speed, accelerate normally.
		else {acceleration = (accelRate/2.5f) * modifier;} //If its still or slow, accelerate slower.
	}
	
	void slowDown() {
		acceleration = (speed/10) * -1;
	}
	
	boolean checkStop() { //Check if the speed and acceleration mean the tank should stop.
		if (speed <= maxSpeed/100 && speed >= (maxSpeed/100 * -1) ) {
			
			return true;
		}else {return false;}
	}
	
	/////////////Cannon-related functions.
	
	void doCannon() {
		if (controller.fire) {
			for(int i=0;i<objects.length;i++) {
				if(objects[i].objectType == "Cannon") {
					Cannon cannon = (Cannon) objects[i];
					cannon.trigger();
				}
			}
		}
	}
	
	/////////////functions related to attached objects
	public void attach(Attachable object) {
		
		int availablePos = -1;
		for(int i=0;i<objects.length;i++) { //Find an available position in the array.
			if (availablePos == -1 && objects[i] == null) {availablePos = i;}
		}
		if(availablePos != -1) {
			objects[availablePos] = object;
			object.tank = this;
		} //If a place was found, attach the object.
	}
	
	void updateObjects(SpriteBatch batch) { //Fix attached objects position & rotation
		for(int i=0; i<objects.length; i++) {
			if (objects[i] != null){
				objects[i].update(batch, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY() ,sprite.getRotation() );
			}
		}
	}
	

	
}	
