package input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import elements.Tank;

public class PlayerInputManager extends InputAdapter {

	Tank tank;
	
	public PlayerInputManager(Tank tank) {
		this.tank = tank;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		
		case Input.Keys.D:
				tank.right = true;
		return true;
		
		case Input.Keys.A:
			tank.left = true;
		return true;
	
		case Input.Keys.W:
			tank.up = true;
		return true;
			
		case Input.Keys.S:
			tank.down = true;
		return true;
		
		case Input.Keys.SPACE:
			tank.fire = true;
		return true;
		
		default:
			return false;
		}
	}
	/** keyUp = al dejar de pulsar una tecla */
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		
		case Input.Keys.D:
			tank.right = false;
		return true;
	
		case Input.Keys.A:
			tank.left = false;
		return true;

		case Input.Keys.W:
			tank.up = false;
		return true;
		
		case Input.Keys.S:
			tank.down = false;
		return true;
		
		case Input.Keys.SPACE:
			tank.fire = false;
		return true;
		
		default:
			return false;
		}
	}

}
