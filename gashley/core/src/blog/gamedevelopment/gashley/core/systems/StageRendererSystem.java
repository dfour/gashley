package blog.gamedevelopment.gashley.core.systems;

import blog.gamedevelopment.gashley.core.components.ButtonActionComponent;
import blog.gamedevelopment.gashley.core.components.ButtonComponent;
import blog.gamedevelopment.gashley.core.components.TextureComponent;
import blog.gamedevelopment.gashley.core.components.TransformComponent;
import blog.gamedevelopment.gashley.core.listnerers.GashleyButtonActionListener;
import blog.gamedevelopment.gashley.core.listnerers.StageListener;
import blog.gamedevelopment.gashley.settings.MainConfig;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StageRendererSystem extends IteratingSystem{

	public Stage stage;
	private ComponentMapper<ButtonComponent> butCom;
	private ComponentMapper<ButtonActionComponent> butActCom;
	private Table rootTable;
	private HorizontalGroup hg;
	private GashleyButtonActionListener listener;
	
	
	@SuppressWarnings("unchecked")
	public StageRendererSystem() {
		super(Family.all(ButtonActionComponent.class,ButtonComponent.class).get());
		stage = new Stage(new ScreenViewport());
		rootTable = new Table();
		rootTable.setFillParent(true);
		rootTable.setDebug(MainConfig.DEBUG);
		rootTable.bottom().pad(0);
		hg = new HorizontalGroup();
		rootTable.add(hg).pad(8);
		stage.addActor(rootTable);
		butCom = ComponentMapper.getFor(ButtonComponent.class);
		butActCom = ComponentMapper.getFor(ButtonActionComponent.class);
	}
	
	public void setButtonListener(GashleyButtonActionListener gbal){
		this.listener = gbal;
		
	}
	
	public void setBackground(NinePatchDrawable patch){
		rootTable.setBackground(patch);
	}


	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		StageListener sl = new StageListener(hg);
		this.getEngine().addEntityListener(this.getFamily(), sl);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		ButtonComponent button = butCom.get(entity);
		ButtonActionComponent buttonAction = butActCom.get(entity);		
		if(button.actor.clicked){
			if(listener != null){
				boolean result = listener.clicked(entity,buttonAction.buttonActionId);
				if(result){
					button.actor.clicked = false;
				}
			}
		}
	}
	
	@Override
    public void update(float deltaTime) {
		super.update(deltaTime);
		stage.act();
		stage.draw();
	}

}
