package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import utilities.Config;
import utilities.Resources;

public class SceneHud {
	public Stage stage;
	private OrthographicCamera camara;
	public Viewport viewport;
	private Table table;
	private Skin skin;
	private Label score;
	private ProgressBar hpBar;
	private ProgressBar bulletBar;

	public SceneHud() {
		camara = new OrthographicCamera();
		viewport = new FitViewport(Config.WIDTH,Config.HEIGHT,camara);
	
		stage = new Stage(viewport);
		// set Skin, file that contains atlas from the sprites, styles and etc
		skin = new Skin(Gdx.files.internal(Resources.SCENESKIN));

		table = new Table();
		table.setFillParent(true);

		score = new Label("ScoreBoard: 0", skin);
		score.setAlignment(Align.topLeft);
		table.add(score).expandX().align(Align.topLeft);
		table.row();

		hpBar = new ProgressBar(-5.0f, 100.0f, 1.0f, false, skin, "BulletBar");
		hpBar.setValue(100.0f);
		
		table.add(hpBar).padBottom(16.0f).expandY().align(Align.bottom).maxWidth(70.0f);

		table.row();
		bulletBar = new ProgressBar(0.0f, 100.0f, 1.0f, false, skin, "hpBar");
		bulletBar.setValue(100.0f);
		//padLeft reason: some pixels that were tilting me off
		table.add(bulletBar).padLeft(-1.0f).expandX().align(Align.bottom);
		stage.addActor(table);
	}

	public void draw() {
		stage.act();
		stage.draw();
	}
}