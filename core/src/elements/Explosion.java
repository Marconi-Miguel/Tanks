package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

import utilities.Config;
import utilities.Render;
import utilities.Resources;

public class Explosion extends Entidad2D{
	//this is gonna be an animation, so we had to start with that.
	private float rad;
	private float radAux;
	private Animation<Sprite> animation;
	private Array<Sprite> frames;
	private int counter;
	//in this case the frameSize will always be 5,(cause we only have 5 sprites xd)
	private int framesSize;
	public Explosion(Texture texture,float x,float y) {
		super(texture);
		world = Render.world;
		rad = 10/Config.PPM;
		setPosition(x,y);
		setAnimation();
	}
	
	private void setAnimation() {
		frames = new Array<Sprite>();
		for (int i = 0; i < framesSize; i++) {
			frames.add(new Sprite(new Texture(Resources.EXPLOSION+ (i+1)+".png")));
		}
		animation = new Animation(0.1f,frames);
	}

	public void update() {
		counter += Config.delta;
		setRegion(getFrame());
		//we define the explosion here cause the sensor would expand
		createBody();
		fixtureDef();
	}
	


	private Sprite getFrame() {
		return animation.getKeyFrame(counter);
	}

	@Override
	protected void createBody() {
		bdef = new BodyDef();
		bdef.position.set(getX(),getY());
		bdef.type = BodyDef.BodyType.KinematicBody;
		b2body = world.createBody(bdef);
		
		
	}

	@Override
	protected void fixtureDef() {
		fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(radAux);
		fdef.filter.categoryBits = Config.EXPLOSION_BIT;
		fdef.shape = shape;
		b2body.createFixture(fdef).setUserData(this);

		
	}



}
