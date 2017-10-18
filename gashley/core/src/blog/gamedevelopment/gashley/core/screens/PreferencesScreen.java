package blog.gamedevelopment.gashley.core.screens;

import blog.gamedevelopment.gashley.core.GashleyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PreferencesScreen extends Scene2dScreenBase{

	private Label titleLabel;
	private Label volumeMusicLabel;
	private Label volumeSoundLabel;
	private Label musicOnOffLabel;
	private Label soundOnOffLabel;
	private TextButton btnBack; 

	
	public PreferencesScreen(GashleyGame p){
		super(p);		
	}

	@Override
	public void show() {
		super.show();
		
		// music volume
		final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
		volumeMusicSlider.setValue(parent.getPreferences().getMusicVolume());
		volumeMusicSlider.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				parent.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
				// updateVolumeLabel();
				return false;
			}
		});
		
		// sound volume
		final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
		soundMusicSlider.setValue(parent.getPreferences().getSoundVolume());
		soundMusicSlider.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				parent.getPreferences().setSoundVolume(soundMusicSlider.getValue());
				// updateVolumeLabel();
				return false;
			}
		});

		// music on/off
		final CheckBox musicCheckbox = new CheckBox(null, skin);
		musicCheckbox.setChecked(parent.getPreferences().isMusicEnabled());
		musicCheckbox.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				boolean enabled = musicCheckbox.isChecked();
				parent.getPreferences().setMusicEnabled(enabled);
				return false;
			}
		});

		// sound on/off
		final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
		soundEffectsCheckbox.setChecked(parent.getPreferences().isSoundEffectsEnabled());
		soundEffectsCheckbox.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				boolean enabled = soundEffectsCheckbox.isChecked();
				parent.getPreferences().setSoundEffectsEnabled(enabled);
				return false;
			}
		});
		
		btnBack = new TextButton("Back", skin, "small");
        
		btnBack.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y){
				PreferencesScreen.this.returnScreen = GashleyGame.MENU;
				PreferencesScreen.this.isReturning = true;	
			}
		});


		titleLabel = new Label("Preferences", skin );
		volumeMusicLabel = new Label( "Music Volume", skin );
		volumeSoundLabel = new Label( "Sound Volume", skin );
		musicOnOffLabel = new Label( "Music", skin );
		soundOnOffLabel = new Label( "Sound Effect", skin );
		
		displayTable.add(titleLabel).colspan(2);
		displayTable.row().pad(10,0,0,10);
		displayTable.add(volumeMusicLabel).left();
		displayTable.add(volumeMusicSlider);
		displayTable.row().pad(10,0,0,10);
		displayTable.add(musicOnOffLabel).left();
		displayTable.add(musicCheckbox);
		displayTable.row().pad(10,0,0,10);
		displayTable.add(volumeSoundLabel).left();
		displayTable.add(soundMusicSlider);
		displayTable.row().pad(10,0,0,10);
		displayTable.add(soundOnOffLabel).left();
		displayTable.add(soundEffectsCheckbox);
		displayTable.row().pad(10,0,0,10);
		displayTable.add(btnBack).colspan(2);
	}
}
