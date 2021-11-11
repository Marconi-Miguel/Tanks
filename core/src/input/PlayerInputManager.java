package input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class PlayerInputManager extends InputAdapter {

	Player localPlayer;
	
	public PlayerInputManager(Player player) {
		this.localPlayer = player;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		
		case Input.Keys.D:
				localPlayer.inputs.replace(InputKeys.RIGHT, true);
		return true;
		
		case Input.Keys.A:
			//localPlayer.left = true;
		return true;
	
		case Input.Keys.W:
			//localPlayer.up = true;
		return true;
			
		case Input.Keys.S:
			//localPlayer.down = true;
		return true;
		
		case Input.Keys.SPACE:
			//localPlayer.fire = true;
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
			localPlayer.inputs.replace(InputKeys.RIGHT, false);
		return true;
	
		case Input.Keys.A:
			//localPlayer.left = false;
		return true;

		case Input.Keys.W:
			//localPlayer.up = false;
		return true;
		
		case Input.Keys.S:
			//localPlayer.down = false;
		return true;
		
		case Input.Keys.SPACE:
			//localPlayer.fire = false;
		return true;
		
		default:
			return false;
		}
	}

}
