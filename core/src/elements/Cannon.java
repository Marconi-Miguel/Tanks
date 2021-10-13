package elements;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import utilities.Functions;
import utilities.Render;
import utilities.Resources;

public class Cannon extends Attachable {
	
	public boolean ready = true;
	public int reloadTime;
	
	Texture idle;
	Texture fire;
	public Sound fireSfx;
	
	Timer timer = new Timer();
	
	public Cannon(String idle, String fire) {
		super(idle);
		this.idle = new Texture(idle);
		this.fire = new Texture (fire);
		objectType = "Cannon";
	}
	
	public void trigger() {
		if (ready){
			ready = false;
			super.modifyTexture(fire);
			fireSfx.play(1,Functions.randomFloat(0.8f,1.2f), 1);
			Timer.schedule(new Task() {
				public void run() {
					idleCannon();
				}
			}, 0.03f);
			Sprite shell = new Projectile(getX() + 45, getY() +80, tank.rotation, Resources.BASICSHELL, 1);
			Render.addImage(shell);
			Timer.schedule(new Task() {
				public void run() {
					ready = true;
				}
			}, reloadTime);
			
		}else {
		}
	}
	
	private void idleCannon() {
		super.modifyTexture(idle);
	}
	

}
