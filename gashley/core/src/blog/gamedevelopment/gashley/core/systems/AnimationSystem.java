package blog.gamedevelopment.gashley.core.systems;

import blog.gamedevelopment.gashley.core.components.AnimationComponent;
import blog.gamedevelopment.gashley.core.components.StateComponent;
import blog.gamedevelopment.gashley.core.components.TextureComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class AnimationSystem extends IteratingSystem {

    ComponentMapper<TextureComponent> tm;
    ComponentMapper<AnimationComponent> am;

    @SuppressWarnings("unchecked")
	public AnimationSystem(){
        super(Family.all(TextureComponent.class,
                AnimationComponent.class,
                StateComponent.class).get());

        tm = ComponentMapper.getFor(TextureComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        AnimationComponent ani = am.get(entity);

        if(ani.animations.containsKey(ani.currentState)){
            TextureComponent tex = tm.get(entity);
            tex.region = ani.animations.get(ani.currentState).getKeyFrame(ani.stateTime, ani.looping.get(ani.currentState));
        }

        ani.stateTime += deltaTime;
    }
}
