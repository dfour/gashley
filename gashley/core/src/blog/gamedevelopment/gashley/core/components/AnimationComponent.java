package blog.gamedevelopment.gashley.core.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool.Poolable;

public class AnimationComponent implements Component, Poolable {
	public IntMap<Animation<TextureRegion>> animations = new IntMap<Animation<TextureRegion>>();
	public IntMap<Boolean> looping = new IntMap<Boolean>();
	public int currentState = 0;
    public int stateTime = 0;

	@Override
	public void reset() {
		animations.clear();
		looping.clear();
		stateTime = 0;
	}
}
