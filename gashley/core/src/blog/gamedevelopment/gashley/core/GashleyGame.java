package blog.gamedevelopment.gashley.core;

import blog.gamedevelopment.gashley.core.loaders.GAssetManager;
import blog.gamedevelopment.gashley.core.screens.GashleyGameScreen;
import blog.gamedevelopment.gashley.core.screens.LoadingScreen;
import blog.gamedevelopment.gashley.core.screens.MenuScreen;
import blog.gamedevelopment.gashley.core.screens.PreferencesScreen;

import com.badlogic.gdx.Game;

public abstract class GashleyGame extends Game {
	public GAssetManager assMan;
	protected GashleyPreferences preferences;
	protected MenuScreen menuScreen;
	protected PreferencesScreen preferencesScreen;
	protected GashleyGameScreen mainScreen;
	protected LoadingScreen loadingScreen;
	
	public static final int LOAD = 0;
	public static final int MENU = 1;
	public static final int GAME = 2;
	public static final int PREFS = 3;
	
	@Override
	public void create() {
		assMan = new GAssetManager();
		preferences = new GashleyPreferences();
		this.changeScreen(LOAD);
	}
	
	public GashleyPreferences getPreferences(){
		return this.preferences;
	}
	
	// return true on loading screen, false if screen not defined in gashley
	public boolean changeScreen(int screen){
		switch(screen){
			case LOAD:
				loadingScreen = new LoadingScreen(this);
				this.setScreen(loadingScreen);
				return true;
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				return true;
			case PREFS:
				if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
				this.setScreen(preferencesScreen);
				return true;
			case GAME:
				// always make new game screen so game can't start midway
				if(mainScreen == null) mainScreen = createMainScreen(this);
				this.setScreen(mainScreen);
				return true;
		}
		
		return false;
	}
	
	public abstract GashleyGameScreen createMainScreen(GashleyGame gg);

	@Override
	public void dispose(){
		assMan.dispose();
	}
}
