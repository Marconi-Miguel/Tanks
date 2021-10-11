package TankData;

import com.badlogic.gdx.graphics.Texture;

import utilities.Resources;

public class BasicHull extends Hull{
	
	public BasicHull(){
		//Sprite config
		texture = new Texture(Resources.BASICHULL);
		originX = 45; originY = 138;
		startRotation = 90;
		
		//Specs
		weaponSlots = 1;
		maxSpeed = 60;
		rotationSpeed = 0.5f;
		accelRate = maxSpeed/50;
		
		slots = weaponSlots; //total amount of slots
	}

}
