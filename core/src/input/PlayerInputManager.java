package input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import network.ClientsideThread;
import network.NetworkCodes;
import utilities.Config;

public class PlayerInputManager extends InputAdapter {

	Player localPlayer;
	ClientsideThread thread;
	float mouseX,mouseY;
	boolean click;
	public PlayerInputManager(Player localPlayer) {//This is the proper way. Don't change it.
		this.localPlayer = localPlayer;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (thread == null) { return false; } //failsafe
		switch (keycode) {
		
		case Input.Keys.D:
				thread.sendMessage(NetworkCodes.INPUT+"RIGHT-"+"TRUE");
		return true;
		
		case Input.Keys.A:
			
			thread.sendMessage(NetworkCodes.INPUT+"LEFT-"+"TRUE");
		return true;
	
		case Input.Keys.W:
			thread.sendMessage(NetworkCodes.INPUT+"UP-"+"TRUE");
		return true;
			
		case Input.Keys.S:
			thread.sendMessage(NetworkCodes.INPUT+"DOWN-"+"TRUE");
		return true;
		
		case Input.Keys.SPACE:
			thread.sendMessage(NetworkCodes.INPUT+"FIRE-"+"TRUE");
		return true;
		
		default:
			return false;
		}
	}
	/** keyUp = al dejar de pulsar una tecla */
	@Override
	public boolean keyUp(int keycode) {
		if (thread == null) { return false; } //failsafe
		switch (keycode) {
		
		case Input.Keys.D:
			thread.sendMessage(NetworkCodes.INPUT+"RIGHT-"+"FALSE");
		return true;
	
		case Input.Keys.A:
			thread.sendMessage(NetworkCodes.INPUT+"LEFT-"+"FALSE");
		return true;

		case Input.Keys.W:
			thread.sendMessage(NetworkCodes.INPUT+"UP-"+"FALSE");
		return true;
		
		case Input.Keys.S:
			thread.sendMessage(NetworkCodes.INPUT+"DOWN-"+"FALSE");
		return true;
		
		case Input.Keys.SPACE:
			thread.sendMessage(NetworkCodes.INPUT+"FIRE-"+"FALSE");
		return true;
		
		default:
			return false;
		}
		
		
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = Config.HEIGHT- screenY;
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		click = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		click = false;
		return false;
	}
	public float getMouseX() {
		return mouseX;
	}
	public float getMouseY() {
		return mouseY;
	}
	public boolean isClick() {
		return click;
	}


}
