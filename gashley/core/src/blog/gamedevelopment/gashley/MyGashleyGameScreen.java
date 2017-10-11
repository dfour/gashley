package blog.gamedevelopment.gashley;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import blog.gamedevelopment.gashley.core.GashleyGame;
import blog.gamedevelopment.gashley.core.screens.GashleyGameScreen;

public class MyGashleyGameScreen extends GashleyGameScreen {

	public MyGashleyGameScreen(GashleyGame p) {
		super(p);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(controller);	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {		
	}

	@Override
	public void pause() {		
	}

	@Override
	public void resume() {
		Gdx.input.setInputProcessor(controller);	
	}

	@Override
	public void hide() {		
	}

}
