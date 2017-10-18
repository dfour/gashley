package blog.gamedevelopment.gashley.core.components;

import blog.gamedevelopment.gashley.core.utils.GuiButtonActor;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ButtonComponent implements Component,Poolable{
	public GuiButtonActor actor;
	public int index = 0;
	
	@Override
	public void reset() {
		actor = null;
		index = 0;
	}

}
