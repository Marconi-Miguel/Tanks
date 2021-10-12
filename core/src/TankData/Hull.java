package TankData;

import com.badlogic.gdx.graphics.Texture;

import elements.ImageClass;

public class Hull extends ImageClass{
	public Hull(String texture){
		super(texture);
	}
//	public int originX;
//	public int originY;
	public int startRotation;
	public int[] hitboxes;
	
	public int weaponSlots;
	public float maxSpeed;
	public float rotationSpeed;
	public float accelRate;
	public int slots;
	
	

}
