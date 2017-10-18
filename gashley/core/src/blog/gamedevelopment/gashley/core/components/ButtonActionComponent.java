package blog.gamedevelopment.gashley.core.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ButtonActionComponent implements Component, Poolable {
	public int buttonActionId = 0; // stores an ID to link to button action

	@Override
	public void reset() {
		buttonActionId = 0;
	}
}
