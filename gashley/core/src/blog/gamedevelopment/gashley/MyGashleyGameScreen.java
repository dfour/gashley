package blog.gamedevelopment.gashley;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

import blog.gamedevelopment.gashley.core.GashleyGame;
import blog.gamedevelopment.gashley.core.components.B2dBodyComponent;
import blog.gamedevelopment.gashley.core.components.ButtonActionComponent;
import blog.gamedevelopment.gashley.core.components.ButtonComponent;
import blog.gamedevelopment.gashley.core.components.ControllableComponent;
import blog.gamedevelopment.gashley.core.components.TextureComponent;
import blog.gamedevelopment.gashley.core.components.TransformComponent;
import blog.gamedevelopment.gashley.core.listnerers.GashleyButtonActionListener;
import blog.gamedevelopment.gashley.core.listnerers.GashleyCollisionListener;
import blog.gamedevelopment.gashley.core.screens.GashleyGameScreen;
import blog.gamedevelopment.gashley.core.systems.ControlSystem;
import blog.gamedevelopment.gashley.core.systems.StageRendererSystem;
import blog.gamedevelopment.gashley.core.utils.BodyFactory;
import blog.gamedevelopment.gashley.core.utils.DFUtils;
import blog.gamedevelopment.gashley.core.utils.GuiButtonActor;

public class MyGashleyGameScreen extends GashleyGameScreen implements GashleyCollisionListener, GashleyButtonActionListener {
	
	private InputMultiplexer im; // input multiplexer for using multiple input sources
	private StageRendererSystem sr;

	public MyGashleyGameScreen(GashleyGame p) {
		super(p); // create world with normal gravity 0,-10f sith super(p). 
		// For worlds with no gravity user super(p,0,0);

		//add a control system
		ControlSystem cs = new ControlSystem();
		cs.setController(this.controller);
		engine.addSystem(cs);
		
		sr = new StageRendererSystem();
		sr.setBackground(new NinePatchDrawable(atlasGui.createPatch("gui")));
		sr.setButtonListener(this);
		engine.addSystem(sr);
		
		im = new InputMultiplexer(sr.stage,controller); //add stage and controller for input sources
		
		
		
		makeDynamicSquareExample();
		makeStaticSquareExample(32f, 1f, 64f, 1f);
		makeControlableCircleExample(11f,11f,1f);
		TextureRegion tex = atlasGui.findRegion("gui_button");
		makeGUIButtonExample(tex,1,1);
		makeGUIButtonExample(tex,2,2);
		makeGUIButtonExample(tex,3,3);
		
	}
	
	private void makeGUIButtonExample(TextureRegion tex, int position, int actionId) {
		GuiButtonActor guiba = new GuiButtonActor(tex);
		
		Entity ent = engine.createEntity();
		ButtonComponent butCom = engine.createComponent(ButtonComponent.class);
		ButtonActionComponent butActCom = engine.createComponent(ButtonActionComponent.class);
		
		butCom.actor = guiba;
		butCom.index = position;
		butActCom.buttonActionId = actionId;
		
		ent.add(butCom);
		ent.add(butActCom);
		
		engine.addEntity(ent);
	}

	private void makeControlableCircleExample(float x, float y, float radius) {
		Entity ent = engine.createEntity();
		B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
		b2dbody.body = bodyFactory.makeCirclePolyBody(x,y,radius, BodyFactory.STONE, BodyType.DynamicBody);
		b2dbody.body.setUserData(ent);
		ent.add(b2dbody);
		
		TextureComponent texture = engine.createComponent(TextureComponent.class);
		texture.region = DFUtils.makeTextureRegion(radius * 16, 16, "000033");
		ent.add(texture);
		
		TransformComponent trans = engine.createComponent(TransformComponent.class);
		trans.position.set(x,y,0);  // z position sets drawing order
		ent.add(trans);
		
		ControllableComponent cont = engine.createComponent(ControllableComponent.class);
		cont.focused = true; // make this the object getting controlled
		cont.speed = 15f; // set the speed it moves
		ent.add(cont);
		
		engine.addEntity(ent);
	}

	private void makeDynamicSquareExample(){
		Entity ent = engine.createEntity();
		B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
		b2dbody.body = bodyFactory.makeBoxPolyBody(32, 24, 1f, 1f, BodyFactory.STONE, BodyType.DynamicBody);
		b2dbody.body.setUserData(ent);
		ent.add(b2dbody);
		
		TextureComponent texture = engine.createComponent(TextureComponent.class);
		texture.region = DFUtils.makeTextureRegion(16, 16, "003300");
		ent.add(texture);
		
		TransformComponent trans = engine.createComponent(TransformComponent.class);
		trans.position.set(32, 24, 0);
		ent.add(trans);
		
		engine.addEntity(ent);
	}
	
	// the floor 
	private void makeStaticSquareExample(float x, float y, float width, float height){
		Entity ent = engine.createEntity();
		B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
		b2dbody.body = bodyFactory.makeBoxPolyBody(x, y, width, height, BodyFactory.STONE, BodyType.StaticBody);
		b2dbody.body.setUserData(ent);
		ent.add(b2dbody);
		
		TextureComponent texture = engine.createComponent(TextureComponent.class);
		texture.region = DFUtils.makeTextureRegion(width * 16, 16, "330000");
		ent.add(texture);
		
		TransformComponent trans = engine.createComponent(TransformComponent.class);
		trans.position.set(x, y, 0);
		ent.add(trans);
		
		engine.addEntity(ent);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(im);	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		sr.stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {		
	}

	@Override
	public void resume() {
		Gdx.input.setInputProcessor(im);	
	}

	@Override
	public void hide() {		
	}

	// listen for contacts between entities
	@Override
	public void collisionStart(Entity a, Entity b) {
		
	}

	@Override
	public void collisionEnd(Entity a, Entity b) {
		
	}

	@Override
	public boolean clicked(Entity source, int actionID) {
		System.out.println(actionID);
		return true;
	}

}
