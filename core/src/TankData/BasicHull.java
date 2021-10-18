package TankData;

import utilities.Resources;

public class BasicHull extends Hull{
	
	public BasicHull(){
		//Sprite config
		super(Resources.BASICHULL,100);
		
		//esto nose si quitarlo, lo comento preguntar despues
//		originX = 45; originY = 138;
		setX(45);
		setY(138);
		startRotation = 90;
		
		//Specs
		weaponSlots = 1;
		maxSpeed = 60;
		rotationSpeed = 0.5f;
		accelRate = maxSpeed/50;
		
		slots = weaponSlots; //total amount of slots
	}

}
