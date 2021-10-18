package TankData;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import utilities.Render;

public class Hull extends Sprite{
	//box2D
	protected FixtureDef fdef;
	protected BodyDef bdef;
	protected Body b2body;
	
	
	
	
	// stats
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
	
	public Hull(String texture){
		super(new Texture(texture));
	}
	
	public void desAparecer() {
		Render.world.destroyBody(b2body);
		b2body=null;
	}
	
	public void setVelocidad(float x, float y) {
		b2body.setLinearVelocity(x, y);
	}

	
	
	

}
