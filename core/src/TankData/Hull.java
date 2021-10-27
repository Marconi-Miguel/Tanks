package TankData;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import utilities.Config;
import utilities.Render;

public class Hull extends Sprite {
	// box2D
	protected FixtureDef fdef;
	protected BodyDef bdef;
	protected Body b2body;
	World world;
	private Sprite dmged1;
	private Sprite dmged2;
	Sprite dmged3;

	public int originX;	
	public int originY;
	public int startRotation;

	// stats
	private int totalHp = 0;
	private int hp = 0;

	public float rotation;
	public int weaponSlots;
	public float maxSpeed;
	public float rotationSpeed;
	public float accelRate;
	public int slots;

	public Hull(String texture, int hp) {
		super(new Texture(texture));
		this.hp = hp;
		this.world = Render.world;
		System.out.println("w:" + getWidth());
		System.out.println("h:" + getHeight());
		System.out.println("W:" + getWidth()/Config.PPM);
		System.out.println("H:" + getHeight()/ Config.PPM );
		setBounds(2, 2, getWidth()/Config.PPM, getHeight()/ Config.PPM );
		
		createBody();

		fixtureDef();
	}

	private void createBody() {
		// new Body
		bdef = new BodyDef();
		// starter position of body
		bdef.position.set(getWidth()/ Config.PPM, getHeight()/ Config.PPM);
		// kind of body and set the map
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

	}

	protected void fixtureDef() {
		fdef = new FixtureDef();
		// defines what kind the box is going to have
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getWidth() / 2, getHeight() / 4);
		fdef.filter.categoryBits = Config.TANK_BIT;
		// definimos la mascara de bits, que objetos box2d tiene que darle atencion.
		fdef.filter.maskBits = Config.DEFAULT_BIT | Config.ROAD_BIT;
		fdef.shape = shape;
		b2body.createFixture(fdef).setUserData("rect");
		sensorsDef();

	}

	protected void sensorsDef() {
		// se crea sensores para todos los sentidos
		EdgeShape up = new EdgeShape();
		EdgeShape down = new EdgeShape();
		//right
		EdgeShape side1 = new EdgeShape();
		//left
		EdgeShape side2 = new EdgeShape();

		// se crea la figura los sensores en este caso lineas
		//it takes the origin in the center of the body
		up.set(new Vector2(-getWidth()/2, getHeight()/2 ), new Vector2(getWidth()/2 , getHeight()/2 ));
		fdef.shape = up;
		fdef.isSensor = true;
		//then it joins to the b2body
		b2body.createFixture(fdef).setUserData("up");
		
		
		down.set(new Vector2(-getWidth() / 2, -getHeight() / 2), new Vector2(getWidth() / 2, -getHeight() / 2));
		fdef.shape = down;
		fdef.isSensor = true;
		b2body.createFixture(fdef).setUserData("down");
		
		
		side1.set(new Vector2(getWidth() / 2, getHeight() / 2),
				new Vector2(getWidth() / 2, -getHeight() /  2));
		fdef.shape = side1;
		fdef.isSensor = true;
		b2body.createFixture(fdef).setUserData("side1");
		
		
		side2.set(new Vector2(-getWidth() / 2, getHeight() / 2 ),
				new Vector2(-getWidth() / 2, -getHeight() / 2));
		fdef.shape = side2;
		fdef.isSensor = true;
		b2body.createFixture(fdef).setUserData("side2");

	}

	public void dissapear() {
		Render.world.destroyBody(b2body);
		b2body = null;
	}

	public void setVelocidad(float x, float y) {
		b2body.setLinearVelocity(x, y);
	}

}
