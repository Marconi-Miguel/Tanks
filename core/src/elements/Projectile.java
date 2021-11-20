package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import utilities.Config;
import utilities.Render;

public class Projectile extends Entidad2D {
	public float speedMod;
	public Hull parent;
	public float degrees;
	public boolean fired = false;
	
	public Projectile(float x, float y, Hull hull, String texture, float speedMod) {
		super(new Texture(texture));
		parent = hull;
		this.world = Render.world;
		this.speedMod = 2;
		setDegrees(hull.rotation);
		setSize(getWidth()/2/Config.PPM, getHeight()/2/Config.PPM);
		setOrigin(getWidth() / 2, getHeight() / 2);
		setPosition(x, y-getHeight()/2);
		
		createBody();
		fixtureDef();

	}
	
	public void fired() {
		doMovement();
		draw(Render.batch);
	}

	void doMovement() {
		float tempY= (float) Math.cos(Math.toRadians(degrees));
		float tempX = (float) Math.sin(Math.toRadians(degrees));
		b2body.setLinearVelocity(-tempX * speedMod, tempY * speedMod);
		setPosition((b2body.getPosition().x - getWidth()/2),// CHANGE / -getWidth/2
				b2body.getPosition().y - getHeight()/2);
		
		
	}

//////////////////////
	
	
	@Override
	protected void createBody() {
		bdef = new BodyDef();
		bdef.position.set(getX(), getY());
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
	}

	@Override
	protected void fixtureDef() {
		fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(getWidth()/2);
		fdef.filter.categoryBits = Config.PROJECTIL_BIT;
		fdef.filter.maskBits = Config.TANK_BIT | Config.OBSTACLE_BIT | Config.EXPLOSION_BIT;
		fdef.shape = shape;
		fdef.isSensor = true;
		b2body.createFixture(fdef).setUserData("projectil");

	}
	public void gotHitted(Hull hitted) {
		System.out.println("toco");
		if(hitted != parent) {
			disappear();
		}
	
	}
	
	@Override
	public void disappear() {
		Render.world.destroyBody(b2body);
		b2body = null;
	}
	public void setDegrees(float degrees) {
		this.degrees = degrees;
		rotate(degrees);
	}


}
