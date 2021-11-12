package input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import utilities.Render;

public class PlayerInputManager extends InputAdapter {

	Player localPlayer;
	
	public PlayerInputManager() {
		this.localPlayer = Render.player;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		
		case Input.Keys.D:
				localPlayer.inputs.replace(InputKeys.RIGHT, false,true);
		return true;
		
		case Input.Keys.A:
			localPlayer.inputs.replace(InputKeys.LEFT, false,true);
		return true;
	
		case Input.Keys.W:
			localPlayer.inputs.replace(InputKeys.UP, false,true);
		return true;
			
		case Input.Keys.S:
			localPlayer.inputs.replace(InputKeys.DOWN, false,true);
		return true;
		
		case Input.Keys.SPACE:
			localPlayer.inputs.replace(InputKeys.FIRE, false,true);
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
			localPlayer.inputs.replace(InputKeys.RIGHT, true,false);
		return true;
	
		case Input.Keys.A:
			localPlayer.inputs.replace(InputKeys.LEFT, true,false);
		return true;

		case Input.Keys.W:
			localPlayer.inputs.replace(InputKeys.UP, true,false);
		return true;
		
		case Input.Keys.S:
			localPlayer.inputs.replace(InputKeys.DOWN, true,false);
		return true;
		
		case Input.Keys.SPACE:
			localPlayer.inputs.replace(InputKeys.FIRE, true,false);
			
		return true;
		
		default:
			return false;
		}
	}

}
