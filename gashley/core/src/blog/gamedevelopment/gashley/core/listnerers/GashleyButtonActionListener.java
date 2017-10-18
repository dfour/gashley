package blog.gamedevelopment.gashley.core.listnerers;

import com.badlogic.ashley.core.Entity;

public interface GashleyButtonActionListener {

	public boolean clicked(Entity source, int actionID);
}
