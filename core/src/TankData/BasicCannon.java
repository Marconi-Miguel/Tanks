package TankData;

import elements.Cannon;
import utilities.Resources;

import com.badlogic.gdx.Gdx;

public class BasicCannon extends Cannon {

	public BasicCannon() {
		super(Resources.BASICCANNON,Resources.BASICCANNON_FIRE);
		
		reloadTime = 2;
		
		fireSfx = Gdx.audio.newSound(Gdx.files.internal(Resources.BASICCANNON_FIRE_SFX));
	}

}
