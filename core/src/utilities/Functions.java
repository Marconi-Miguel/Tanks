package utilities;

import java.util.Random;

public class Functions {
	
	
	public static float randomFloat(float min, float max) {
		Random r = new Random();
		float random = min + r.nextFloat() * (max - min);
		return (float) random;
	}
}
