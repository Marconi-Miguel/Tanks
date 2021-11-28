package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import utilities.Config;
import utilities.Functions;
import utilities.Render;
import utilities.Resources;

public class CooldownBuff extends Buff{

	public CooldownBuff() {
		super(new Texture(Resources.CDBUFF));
		b2body.setUserData("cooldown");
	}

	
}
