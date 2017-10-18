package blog.gamedevelopment.gashley.core.screens;

import blog.gamedevelopment.gashley.core.GashleyGame;
import blog.gamedevelopment.gashley.settings.MainConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Scene2dScreenBase implements Screen{
	protected Stage stage;
	protected Skin skin;
	protected GashleyGame parent;
	protected SpriteBatch pb;
	protected Table rootTable;
	protected Table displayTable;
	protected int sw;
	protected int sh;
	protected TextureAtlas atlasGui;
	protected TiledDrawable bgTiled;
	protected float transitionSpeed = 1f;
	protected float fadeIn = transitionSpeed;
	protected float fadeOut = transitionSpeed;  //temp debug speed to make it quick
	protected boolean isReturning = false;
	protected int returnScreen;
	
	public Scene2dScreenBase (GashleyGame p ){
		parent = p;
		stage = new Stage(new ScreenViewport());
		pb = new SpriteBatch();
		
		atlasGui = parent.assMan.manager.get("images/loading.atlas");
		bgTiled = new TiledDrawable(atlasGui.findRegion("background"));
		skin = parent.assMan.manager.get("skin/glassy-ui.json",Skin.class);
		
	}

	@Override
	public void show() {
		Image title = new Image(atlasGui.findRegion("logo"));

		
		rootTable = new Table();
		rootTable.setFillParent(true);
		rootTable.setDebug(MainConfig.DEBUG);
		rootTable.add(title).pad(10);
		rootTable.row().expandY();
		displayTable = new Table();
		displayTable.setDebug(MainConfig.DEBUG);
		//displayTable.setBackground(new NinePatchDrawable(atlasGui.createPatch("darkblockbutton")));
	    displayTable.pad(30);
		rootTable.add(displayTable);
		stage.addActor(rootTable);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.4f, .4f, .4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// new better tiling
		pb.begin();
		bgTiled.draw(pb, 0, 0, sw, sh);
		pb.end();
		
		
		
		if(fadeIn > 0){
			fadeIn -= delta;
			displayTable.setColor(1,1,1,1-fadeIn);
		}else if(this.isReturning){
			fadeOut -= delta;
			displayTable.setColor(1,1,1,fadeOut);
			if(fadeOut <= 0){
				parent.changeScreen(returnScreen);
				this.isReturning = false;
				this.fadeOut = transitionSpeed;
				this.fadeIn = transitionSpeed;
			}
		}
		
		stage.act();
		stage.draw();		
	}

	@Override
	public void resize(int width, int height) {
		sw = width;
		sh = height;
		pb.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		stage.getViewport().update(width, height, true);
		
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
		stage.dispose();
		skin.dispose();
	}

}
