package blog.gamedevelopment.gashley.core.listnerers;

import com.badlogic.ashley.core.Entity;

public interface GashleyCollisionListener {
	
	//called when a collision first occurs between 2 entities
	public void collisionStart(Entity a, Entity b);
	
	//called when a collision ends between 2 entities
	public void collisionEnd(Entity a, Entity b);

}
