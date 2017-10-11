package blog.gamedevelopment.gashley;

import blog.gamedevelopment.gashley.core.GashleyGame;
import blog.gamedevelopment.gashley.core.screens.GashleyGameScreen;

public class Gashely extends GashleyGame {
	
	@Override
	public void create() {
		super.create();
	}

	@Override
	public GashleyGameScreen createMainScreen(GashleyGame gg) {
		return new MyGashleyGameScreen(gg);
	}
}
