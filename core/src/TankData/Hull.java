package TankData;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Hull extends Sprite{
	public Hull(String texture){
		super(new Texture(texture));
	}
	
	public int originX;
	public int originY;
	public int startRotation;
	public int[] hitboxes;
	
	
	public float rotation;
	public int weaponSlots;
	public float maxSpeed;
	public float rotationSpeed;
	public float accelRate;
	public int slots;
	

}
