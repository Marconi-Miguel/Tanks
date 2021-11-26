package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import elements.Tank;
import input.Player;
import input.PlayerInputManager;
import tiledObjects.World2D;
import tiledObjects.WorldListener;
import utilities.Config;
import utilities.Render;
import utilities.Resources;

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
	// object listener
	private WorldListener worldListener;

	// setting inputListener
	private PlayerInputManager PIM;
	// test
	private Sprite imgTest;
	// hull testing
	private Tank tank;
	private Tank tank2;
	private Player localPlayer;

	float time;

	public MapScreen() {

		///// NETWORK TEST
		System.out.println("asd");
		localPlayer = new Player("testPlayer");
		
		
		localPlayer.connect("26.29.247.173", 9995);///// NETWORK TEST
		System.out.println("asd");
		///// setting the PIM AS InputProcessor
		Gdx.input.setInputProcessor(localPlayer.PIM);

		// setting map
		camera = new OrthographicCamera();
		// load tiledMap
		mapLoader = new TmxMapLoader();
		map = mapLoader.load(Resources.MAP1);

		// order the render which map is going to draw
		renderer = new OrthogonalTiledMapRenderer(map, 1 / Config.PPM);

	}

	@Override
	public void show() {
		// set map properties
		Render.world = new World(new Vector2(0, 0), true);
		world = Render.world;
		System.out.println(world.isLocked());
		// render which draws box2d Textures
		b2dr = new Box2DDebugRenderer();
		// then camera zoom
		gamePort = new FitViewport(((64 * 15) / Config.PPM), ((64 * 15) / Config.PPM), camera);
		// centers the camera to the new map
		camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		// creates 2dmap per layers
		world2d = new World2D(map);

		// set the world contact listener
		worldListener = new WorldListener();
		world.setContactListener(worldListener);
		// working with tank
		tank = new Tank(localPlayer);
		Render.tanks.add(tank);
//		tank2 = new Tank(localPlayer2);

		b = Render.batch;
		gamePort.getCamera().position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

	}

	@Override
	public void render(float delta) {
		gamePort.apply();
		update(delta);
		Render.cleanScreen();
		b.setProjectionMatrix(camera.combined);
		// loads map
		renderer.render();
		// loads box2dDebugLines hitboxes
		b2dr.render(world, camera.combined);
		// drawing
		// testing
		b.begin();
		if(tank.hull.getHp()>0) {
			tank.Render();
		}else {
			System.out.println("asd");
			tank.destroy();
		}
		
//		tank2.Render();

		b.end();
		// testing

	}

	private void update(float delta) {

		Config.delta = delta;
		camera.update();
		// 60 ticks in a second if im right
		world.step(1 / 60f, 6, 2);
		// set Camera on the players tank
//		camera.position.x = tank.hull.getX();
//		camera.position.y = tank.hull.getY();
		// sets whats the renderer gonna draw, that shows in camera
		renderer.setView(camera);
		time += delta;


	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
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
		world.dispose();

	}

}
