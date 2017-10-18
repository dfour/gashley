package blog.gamedevelopment.gashley.core.utils;

import blog.gamedevelopment.gashley.settings.MainConfig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class GuiButtonActor extends Actor {
	private TextureRegion texture;
	public boolean clicked = false;

	public GuiButtonActor(TextureRegion tex) {
		texture = tex;
		this.setBounds(this.getX(), this.getY(), 16, 16);
		this.setTouchable(Touchable.enabled);
		this.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	if(MainConfig.DEBUG_BUTTON_ACTIONS) System.out.println("Button Clicked");
                GuiButtonActor.this.clicked = true;
                return true;
            }
        });
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, this.getX(), this.getY());
	}
	
	
	
}
