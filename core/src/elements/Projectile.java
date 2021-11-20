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
	public boolean explode = false;
	public Texture invPix;
	
	public Projectile(float x, float y, Hull hull, String texture, float speedMod) {
		super(new Texture(texture));
		invPix = new Texture("Tanks/Especial/invisiblePixel.png");
		parent = hull;
		this.world = Render.world;
		this.speedMod = speedMod;
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
		b2body.createFixture(fdef).setUserData(this);

	}
	public void gotHitted(Hull hitted) {
		
		if(hitted != parent) {
			explode = true;
		}
	
	}
	
	@Override
	public void disappear() {
		System.out.println("im before body");
		Render.world.destroyBody(b2body);
		System.out.println("im after body :)");
		b2body = null;
		
	
		
		
	}
	public boolean isExploded() {
		return explode;
	}
	public void explode() {
		setTexture(invPix);
		speedMod = 0;
		explode = true;
	}
	public void setDegrees(float degrees) {
		this.degrees = degrees;
		rotate(degrees);
	}


}
