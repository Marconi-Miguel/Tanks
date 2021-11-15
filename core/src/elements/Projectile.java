package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import utilities.Config;
import utilities.Render;

public class Projectile extends Entidad2D {
	public float speed;
	public Tank parent;
	public int rotation;
	public boolean fired = false;
	public Projectile(float x, float y, Tank tank, String texture, float speed) {
		super(new Texture(texture));
		parent = tank;
		this.speed = speed;
		
		setX(x);
		setY(y);
		setSize(this.getWidth() / Config.PPM, this.getHeight() / Config.PPM);
		setRotation(rotation);
		this.world = Render.world;
		createBody();
		fixtureDef();

	}

	public void fired() {
		super.draw(Render.batch);
		doMovement();
	}

	void doMovement() {
		float tempX = (float) Math.cos(Math.toRadians(rotation));
		float tempY = (float) Math.sin(Math.toRadians(rotation));
		b2body.setLinearVelocity(-tempX, tempY);
		setPosition((b2body.getPosition().x -getWidth() / 2),
				b2body.getPosition().y - getHeight() / 2);
		
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
		fdef.filter.categoryBits = Config.TANK_BIT;
		fdef.filter.maskBits = Config.TNKSENSOR_BIT | Config.OBSTACLE_BIT | Config.EXPLOSION_BIT;
		fdef.shape = shape;
		b2body.createFixture(fdef).setUserData("projectil");

	}

	@Override
	protected void disappear() {
		Render.world.destroyBody(b2body);
		b2body = null;
	}

}
