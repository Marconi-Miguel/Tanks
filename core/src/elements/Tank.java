package elements;

import TankData.Hull;
import input.Player;
import utilities.Render;

public class Tank {
	Hull hull;
	Player owner;
	float rotation;
	float maxSpeed;
	float rotationSpeed;
	float accelRate;
	float speed;
	float acceleration;
	boolean forward; //direction. true if going forward, false if reverse.
	
	public boolean up,down,right,left,fire; //control variables


	//Array holding other elements of the tank, such as the cannon.
	Attachable[] objects;
	
	
	public Tank(Hull hull,int x, int y, Player player) {
		
		owner = player;
		hull.setPosition(x,y);
		hull.setOrigin(hull.originX,hull.originY);
		objects = new Attachable[hull.slots];
		
		
	}
	
	public void Render() {
		doMovement();
		doCannon();
		hull.draw(Render.batch);
		updateObjects();//Update other sprites attached to this tank, such as cannon.
		//System.out.println("ACCEL: "+acceleration+" | SPEED: "+speed);
	}
	
	public void setPosition(float x, float y) {
		hull.setPosition(x,y);	
	}
	
	public void rotate(float degrees) {	
		hull.rotate(degrees);
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
		if(right) {
			rotate(rotationSpeed * -1);
		}
		
		if(left) {
			rotate(rotationSpeed);
			
		}
		
		float tempX = (float) Math.cos(Math.toRadians(rotation) ) * speed/40;
		float tempY = (float) Math.sin(Math.toRadians(rotation) ) * speed/40;
		setPosition(hull.getX() + tempX * -1, hull.getY() + tempY * -1);
	}
	
	private void doSpeed() {
		if (up && !down) { //If pressing W, go forward.
			accelerate(true);
		}else if (down && !up) { //If pressing S, go reverse
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
		if (fire) {
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
	
	void updateObjects() { //Fix attached objects position & rotation
		for(int i=0; i<objects.length; i++) {
			if (objects[i] != null){
				objects[i].update(hull.getX(), hull.getY(), hull.getOriginX(), hull.getOriginY() , hull.getRotation() );
			}
		}
	}
	

	
}	
