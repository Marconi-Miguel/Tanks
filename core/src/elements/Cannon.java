package elements;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

import utilities.Config;
import utilities.Render;
import utilities.Resources;

public class Cannon extends Attachable {

	public boolean ready = true;
	public int reloadTime = 2;
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>(); 
	Texture idle;
	Texture fire;
	public Sound fireSfx;
	public int time;

	Timer timer = new Timer();

	public Cannon(String idle, String fire) {
		super(idle);
		this.idle = new Texture(idle);
		this.fire = new Texture(fire);
		objectType = "Cannon";
	}

	public void trigger() {
		if (ready) {
			ready = false;
			modifyTexture(fire);
//			fireSfx.play(1,Functions.randomFloat(0.8f,1.2f), 1);
			Projectile shell = new Projectile(getX() + getWidth()/2, getY()+ getHeight()/2, tank, Resources.BASICSHELL, 25);
			
			projectiles.add(shell);
		}
	}
	public void updateCannon() { 
		time+= Config.delta;
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).fired();
			
		}
	}
	private void idleCannon() {
		modifyTexture(idle);
	}

}
