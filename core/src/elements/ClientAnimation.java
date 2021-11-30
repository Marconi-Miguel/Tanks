package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import utilities.Config;
import utilities.Render;
import utilities.Resources;

public class ClientAnimation extends ClientSprite {

	private float x, y;
	private float counter;
	private Animation<Sprite> animation;
	private Array<Sprite> frames;
	private float framesSize = 5;
	public boolean end;
	
	public ClientAnimation(float x,float y) {
		setPosition(x,y);
		setAnimation();
		setRegion(new Texture(Resources.EXPLOSION+1+".png"));
		Render.addSprite(this);
		super.setPosition(x, y);
	}

	public void update() {
		
		counter+= Config.delta;	
		if(counter < (framesSize/10)) {
			setRegion(getFrame());
			System.out.println(counter);
			setSize(20/Config.PPM,20/Config.PPM);
				
		}else {
			end = true;
		}
	}		
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	private Sprite getFrame() {
		return animation.getKeyFrame(counter);
	}
	private void setAnimation() {
		frames = new Array<Sprite>();
		for (int i = 0; i < framesSize; i++) {
			//change the route when add another animation.
			frames.add(new Sprite(new Texture(Resources.EXPLOSION+ (i+1)+".png")));
		}
		animation = new Animation<Sprite>(0.1f,frames);
	}
}
