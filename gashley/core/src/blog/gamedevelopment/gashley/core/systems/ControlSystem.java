package blog.gamedevelopment.gashley.core.systems;

import blog.gamedevelopment.gashley.core.components.B2dBodyComponent;
import blog.gamedevelopment.gashley.core.components.ControllableComponent;
import blog.gamedevelopment.gashley.core.input.GashleyBaseController;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.InputProcessor;


public class ControlSystem extends IteratingSystem{

	private ComponentMapper<ControllableComponent> cont;
	private ComponentMapper<B2dBodyComponent> bodm;
	private GashleyBaseController controller;
	
	@SuppressWarnings("unchecked")
	public ControlSystem() {
		super(Family.all(ControllableComponent.class,B2dBodyComponent.class).get());
		cont = ComponentMapper.getFor(ControllableComponent.class);
		bodm = ComponentMapper.getFor(B2dBodyComponent.class);
	}
	
	@SuppressWarnings("unchecked")
	public ControlSystem(GashleyBaseController controller) {
		super(Family.all(ControllableComponent.class,B2dBodyComponent.class).get());
		this.controller = controller;
		cont = ComponentMapper.getFor(ControllableComponent.class);
		bodm = ComponentMapper.getFor(B2dBodyComponent.class);
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		B2dBodyComponent b2body = bodm.get(entity);
		ControllableComponent controlled = cont.get(entity);	
		
		if(controlled.focused && controller != null){
			if(controller.up){
				b2body.body.applyForceToCenter(0,controlled.speed * b2body.body.getMass(),true);
			}
			if(controller.down){
				b2body.body.applyForceToCenter(0,-controlled.speed * b2body.body.getMass(),true);
			}
			if(controller.left){
				b2body.body.applyForceToCenter(-controlled.speed * b2body.body.getMass(),0,true);
			}
			if(controller.right){
				b2body.body.applyForceToCenter(controlled.speed * b2body.body.getMass(),0,true);
			}
		}
	}

	public void setController(GashleyBaseController controller) {
		this.controller = controller;		
	}
}

