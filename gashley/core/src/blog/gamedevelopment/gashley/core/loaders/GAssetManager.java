package blog.gamedevelopment.gashley.core.loaders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

public class GAssetManager {
	public Array<String> textureAtlases = new Array<String>();
	public Array<String> bitmapfonts = new Array<String>();
	public ArrayMap<String,ParticleEffectParameter> particleEffects = new ArrayMap<String,ParticleEffectParameter>();
	public Array<String> sounds = new Array<String>();
	public Array<String> music = new Array<String>();
	
	public final AssetManager manager = new AssetManager();
	
	// Textures
	public final String imagesPack = "images/game.atlas";
	public final String guiPack = "images/gui.atlas";
	
	public void addTextureAtlas(String fileLocation){
		textureAtlases.add(fileLocation);
	}
	
	public void addImagesToLoadingQueue(){
		manager.load(imagesPack, TextureAtlas.class);
		manager.load(guiPack, TextureAtlas.class);
		for(String fileLoc:textureAtlases){
			manager.load(fileLoc,TextureAtlas.class);
		}
	}
	
	// fonts
	public final String visfont = "font/visitor.fnt";
	
	public void addBitmapFont(String fileLocation){
		bitmapfonts.add(fileLocation);
	}
	
	public void addFontsToLoadingQueue(){
		manager.load(visfont, BitmapFont.class);
		for(String fileLoc:bitmapfonts){
			manager.load(fileLoc,BitmapFont.class);
		}
	}
	
	// Particle Effects
	public final String sparksPE		="particles/fire.pe";

	public void addParticleEffect(String fileLocation, ParticleEffectParameter pep){
		particleEffects.put(fileLocation,pep);
	}
	
	public void addParticleEffectsToLoadingQueue(){
		ParticleEffectParameter pep = new ParticleEffectParameter(); 
		pep.atlasFile = "images/game.atlas";
		manager.load(sparksPE, ParticleEffect.class, pep);
		for(Entry<String,ParticleEffectParameter> e:particleEffects){
			manager.load(e.key, ParticleEffect.class, e.value);
		}
		
	}
	
	//Sounds
	public final String boingsound	= "sounds/boing.wav";

	public void addSounds(String fileLocation){
		sounds.add(fileLocation);
	}
	
	public void addSoundsToLoadingQueue(){
		manager.load(boingsound, Sound.class);
		for(String fileLoc:sounds){
			manager.load(fileLoc,Sound.class);
		}
	}
	
	//Music
	public final String introMusic 		= "music/Rolemusic_-_pl4y1ng.mp3";

	public void addMusic(String fileLocation){
		music.add(fileLocation);
	}
	
	public void addMusicToLoadingQueue(){
		manager.load(introMusic, Music.class);
		for(String fileLoc:music){
			manager.load(fileLoc, Music.class);
		}
	}
	
	// Skin
	public final String skin = "skin/glassy-ui.json";
	
	public void addLoadingSkinToLoadingueue(){
		SkinParameter params = new SkinParameter("skin/glassy-ui.atlas");
		manager.load(skin, Skin.class, params);
	}

	public final String loadingImages 	= "images/loading.atlas";
	
	public void addLoadingImagesToLoadingQueue(){
		manager.load(loadingImages, TextureAtlas.class);
	}
	
	public void dispose(){
		manager.dispose();
	}
}

