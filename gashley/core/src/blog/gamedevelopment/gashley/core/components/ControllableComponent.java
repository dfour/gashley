package blog.gamedevelopment.gashley.core.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ControllableComponent implements Component,Poolable{
	public boolean focused = false; // only focused unit is controlled
	public float speed = 0;
	
	
	@Override
	public void reset() {
		focused = false;
		speed = 0;
	}
}
