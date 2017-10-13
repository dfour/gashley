package blog.gamedevelopment.gashley;

import blog.gamedevelopment.gashley.core.GashleyGame;
import blog.gamedevelopment.gashley.core.screens.GashleyGameScreen;

public class Gashely extends GashleyGame {
	// TODO List
	/*
	 * Separate controls into 4 way, 8 way, omni
	 * Add components:
	 * Shooting, Bullet, Health
	 * TMX map system, and components
	 */
	
	@Override
	public void create() {
		super.create();
	}

	@Override
	public GashleyGameScreen createMainScreen(GashleyGame gg) {
		return new MyGashleyGameScreen(gg);
	}
}
