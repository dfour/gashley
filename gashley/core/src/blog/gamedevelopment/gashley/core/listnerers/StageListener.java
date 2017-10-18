package blog.gamedevelopment.gashley.core.listnerers;

import blog.gamedevelopment.gashley.core.components.ButtonActionComponent;
import blog.gamedevelopment.gashley.core.components.ButtonComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;

public class StageListener implements EntityListener{

	private HorizontalGroup horizontalGroup;
	private ComponentMapper<ButtonComponent> butCom = ComponentMapper.getFor(ButtonComponent.class);
	//private ComponentMapper<ButtonActionComponent> butActCom = ComponentMapper.getFor(ButtonActionComponent.class);
	
	public StageListener(HorizontalGroup horizontalGroup){
		this.horizontalGroup = horizontalGroup;
	}
	
	@Override
	public void entityAdded(Entity entity) {
		ButtonComponent buttonComp = butCom.get(entity);
		horizontalGroup.addActorAt(buttonComp.index, buttonComp.actor);
		//horizontalGroup.invalidate();
	}

	@Override
	public void entityRemoved(Entity entity) {
		ButtonComponent buttonComp = butCom.get(entity);
		horizontalGroup.removeActor(buttonComp.actor);
		//horizontalGroup.invalidate();
	}

}
