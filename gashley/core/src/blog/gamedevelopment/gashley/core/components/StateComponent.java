package blog.gamedevelopment.gashley.core.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class StateComponent implements Component, Poolable {
	public int state = 0;

	@Override
	public void reset() {
		state = 0;	
	}
}
