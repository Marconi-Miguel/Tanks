package elements;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

import utilities.Render;
import utilities.Resources;

public class Cannon extends Attachable {

	public boolean ready = true;
	public int reloadTime;
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>(); 
	Texture idle;
	Texture fire;
	public Sound fireSfx;

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
			Projectile shell = new Projectile(getX(), getY(), tank, Resources.BASICSHELL, 1);
			projectiles.add(shell);
		}
	}

	private void idleCannon() {
		modifyTexture(idle);
	}

}
