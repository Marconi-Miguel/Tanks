package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import input.PlayerInputManager;
import listeners.WorldListener;
import utilities.Config;
import utilities.Render;
import utilities.Resources;
import utilities.World2D;

public class MapScreen implements Screen {

	private SpriteBatch b;

	// camera
	private OrthographicCamera camera;
	private Viewport gamePort;
	// define tiledMap vars
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	// world
	private World world;
	private Box2DDebugRenderer b2dr;
	// keyBoard 
	private PlayerInputManager inputManager;
	// we have to make the world2d and objects here
	private World2D world2d;
	//object listener
	private WorldListener worldListener;
	//
	
	public MapScreen() {
		// se setea el tipo de camara
			camera = new OrthographicCamera();
		// then camera zoom
			gamePort = new FitViewport(Config.WIDTH / 2 / Config.PPM, Config.HEIGHT / 2 / Config.PPM, camera);
			// load tiledMap
			mapLoader = new TmxMapLoader();
			map = mapLoader.load(Resources.MAP1);
			// order the render which map is going to draw
			renderer = new OrthogonalTiledMapRenderer(map, 1 / Config.PPM);
			// centers the camera to the new map 
			camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
			// set map properties 
			Render.world = new World(new Vector2(0, 0), true);
			world = Render.world;
			//render which draws box2d Textures
			b2dr = new Box2DDebugRenderer();
			
			// creates 2dmap per layers
			world2d = new World2D(world, map);
			
			// set the world contact listener
			world.setContactListener(worldListener);
			
	}
	
	@Override
	public void show() {
		b = Render.batch;
		camera.position.set(Config.WIDTH/2,Config.HEIGHT/2,0);
		gamePort.getCamera().position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
	}

	@Override
	public void render(float delta) {
		Render.cleanScreen();
		b.setProjectionMatrix(camera.combined);
		// loads map
		renderer.render();
		// loads box2dDebugLines hitboxes
		b2dr.render(world, camera.combined);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
