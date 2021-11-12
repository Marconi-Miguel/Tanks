package elements;

import TankData.Hull;
import input.InputKeys;
import input.Player;
import utilities.Config;
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
	boolean forward; //direction. true if going forward, false if reverse.
	
	public boolean up,down,right,left,fire; //control variables


	//Array holding other elements of the tank, such as the cannon.
	Attachable[] objects;
	
	
	public Tank(Hull hull,int x, int y, Player player) {
		
		owner = player;
		this.hull = hull;
		rotationSpeed = 1;
		
		
		objects = new Attachable[hull.slots];
		
		
	}
	
	public void Render() {
		doMovement();
		doCannon();
		//apply to relate b2body with its sprite
//				setPosition((hull.b2body.getPosition().x - (getWidth() - difAnchoRegion) / 2), 
//						b2body.getPosition().y - ((getHeight() - difAltoRegion) / 4)); dont delete this, maybe it could help later
		setPosition((hull.b2body.getPosition().x - hull.getWidth()  / 2), 
				hull.b2body.getPosition().y - hull.getHeight()  / 2); 
		
		hull.draw(Render.batch); // this is the original way to draw the tank.
		
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
		if(owner.inputs.get(InputKeys.RIGHT)) {
			rotate(rotationSpeed * -1);
		}
		
		if(owner.inputs.get(InputKeys.LEFT)) {
			rotate(rotationSpeed);
		}
		
		float tempX = (float) Math.cos(Math.toRadians(rotation)) ;
		float tempY = (float) Math.sin(Math.toRadians(rotation)) ;
		setPosition((hull.getX() + tempX * -1)/Config.PPM,(hull.getY() + tempY * -1)/Config.PPM);
	}
	
	private void doSpeed() {
		if (owner.inputs.get(InputKeys.UP) && !owner.inputs.get(InputKeys.DOWN)) { //If pressing W, go forward.
//			accelerate(true);
			hull.b2body.setLinearVelocity(0, 1);
		}else if (owner.inputs.get(InputKeys.DOWN)&& !owner.inputs.get(InputKeys.UP)) { //If pressing S, go reverse
//			accelerate(false);
			hull.b2body.setLinearVelocity(0, -1);
		}else {
			hull.b2body.setLinearVelocity(0, 0);
		}
		speed += acceleration;
		if(speed > 0 && speed >= maxSpeed) {
			speed = maxSpeed;
		}else if (speed < 0 && speed <= (maxSpeed/2) * -1 ) {
			speed = (maxSpeed/2) * -1;
		}
		
	}
	
//	void accelerate(boolean forward) {
//		this.forward = forward;
//		float modifier;
//		modifier = (forward)?1:-0.5f;//Handle forward/reverse and it's modifier. (-50% speed on reverse)
//		
//		if( ( (speed > 0 && !forward) || (speed < 0 && forward) ) && !checkStop() ) { //If changing directions.
//			slowDown();
//		}else if(speed > maxSpeed/5) {acceleration = accelRate * modifier;} //If it's up to speed, accelerate normally.
//		else {acceleration = (accelRate/2.5f) * modifier;} //If its still or slow, accelerate slower.
//	}
//	
//	void slowDown() {
//		acceleration = (speed/10) * -1;
//	}
//	
//	boolean checkStop() { //Check if the speed and acceleration mean the tank should stop.
//		if (speed <= maxSpeed/100 && speed >= (maxSpeed/100 * -1) ) {
//			
//			return true;
//		}else {return false;}
//	}
	
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
