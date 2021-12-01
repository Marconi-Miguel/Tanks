package input;

import java.util.HashMap;
import java.util.Map;

public class Client {

	public String username;
	public Map<InputKeys, Boolean> inputs = new HashMap<InputKeys, Boolean>();

	public Client() {
		initializeInputs();
	}

	protected void initializeInputs() {
		InputKeys[] array = InputKeys.values(); // Take all the enums InputKeys and put them in an array.
		for (int i = 0; i < array.length; i++) {
			inputs.put(array[i], false); // Use the array of enums to put each enum in the inputs list.
		}
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
