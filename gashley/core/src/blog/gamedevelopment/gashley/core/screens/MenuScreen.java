package blog.gamedevelopment.gashley.core.screens;

import blog.gamedevelopment.gashley.core.GashleyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends Scene2dScreenBase {

	private TextButton btnStart;
	private TextButton btnPrefs;
	private TextButton btnQuit;

	public MenuScreen(GashleyGame p){
		super(p)	;
	}
	
	@Override
	public void show() {
		super.show();
		
		//create buttons
		btnStart = new TextButton("New Game", skin);
		btnPrefs = new TextButton("Preferences", skin);
		btnQuit = new TextButton("Exit", skin);

        displayTable.add(btnStart).width(300).height(50);
        displayTable.row().pad(10, 0, 0, 0);
        displayTable.add(btnPrefs).width(300).height(50);
        displayTable.row().pad(10, 0, 0, 0);
        displayTable.add(btnQuit).width(300).height(50);

		

		btnStart.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y){
				MenuScreen.this.returnScreen = GashleyGame.GAME;
				MenuScreen.this.isReturning = true;	
				}
		});
		
		btnPrefs.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y){
				MenuScreen.this.returnScreen = GashleyGame.PREFS;
				MenuScreen.this.isReturning = true;	
			}
		});
		
		btnQuit.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y){
				Gdx.app.exit();
			}
		});
		
		btnStart.setChecked(false);
		btnPrefs.setChecked(false);
		btnQuit.setChecked(false);	
	}
}

