package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import utilities.Config;
import utilities.ClientRender;
import utilities.Resources;

public class MenuScene {
	public Stage stage;
	private OrthographicCamera camara;
	public Viewport viewport;
	private Table table;
	private Skin skin;	
	private TextField ipTxtFld;
	private TextField portTxtFld;
	private Label ip;
	private Label port; 

	
	public MenuScene(){
		camara = new OrthographicCamera();
		viewport = new FitViewport(Config.WIDTH,Config.HEIGHT,camara);
	
		stage = new Stage(viewport);
		// set Skin, file that contains atlas from the sprites, styles and etc
		skin = new Skin(Gdx.files.internal(Resources.SCENESKIN));

		table = new Table();
		table.setFillParent(true);
		
		ip = new Label("ip", skin);
		ip.setColor(Color.WHITE);
		table.add(ip).expandX().align(Align.center).padTop(-40);
		
		table.row();
		
		ipTxtFld = new TextField("", skin);
		ipTxtFld.setSize(100, 50);
		ipTxtFld.setClipboard(null);
		table.add(ipTxtFld).expandX().align(Align.center).minWidth(200).maxHeight(30).padTop(-40);
		
		table.row();
		
		port = new Label("username", skin);
		port.setColor(Color.WHITE);
		table.add(port).expandX().align(Align.center).padTop(-40);
		
		table.row();
		portTxtFld = new TextField("", skin);
		table.add(portTxtFld).expandX().align(Align.center).padTop(-40).minWidth(100).maxHeight(30);


		stage.addActor(table);
	}
	public void draw() {
		ClientRender.batch.begin();
		stage.act();
		stage.draw();
		ClientRender.batch.end();
	}
	public String getIp() {
		return ipTxtFld.getText();
	}
	public String getUsername() {
		return portTxtFld.getText();
	}
}
