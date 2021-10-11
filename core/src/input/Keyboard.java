package input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class Keyboard extends InputAdapter {

	VehicleController controller;
	
	public Keyboard(VehicleController controller) {
		this.controller = controller;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		
		case Input.Keys.D:
				controller.right = true;
		return true;
		
		case Input.Keys.A:
			controller.left = true;
		return true;
	
		case Input.Keys.W:
			controller.up = true;
		return true;
			
		case Input.Keys.S:
			controller.down = true;
		return true;
		
		case Input.Keys.SPACE:
			controller.fire = true;
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
			controller.right = false;
		return true;
	
		case Input.Keys.A:
			controller.left = false;
		return true;

		case Input.Keys.W:
			controller.up = false;
		return true;
		
		case Input.Keys.S:
			controller.down = false;
		return true;
		
		case Input.Keys.SPACE:
			controller.fire = false;
		return true;
		
		default:
			return false;
		}
	}

}
