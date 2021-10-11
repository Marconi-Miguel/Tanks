package utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.bws.tanks.Tanks;
import java.util.ArrayList;
import java.util.Random;

public abstract class Render {
	public static SpriteBatch batch;
	public static Tanks app;
	public static World mundo;
	ArrayList<Image> images = new ArrayList<Image>();
	
	public static void cleanScreen() {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void renderList(){
		for(int i=0; i<images.size(); i++){
			images.get(i).draw();
		}
	}
	public static int getRandom(int min,int max) {
		Random r = new Random();
		int res = r.nextInt(max+1-min)+min;
		return res;
	}
	
}
