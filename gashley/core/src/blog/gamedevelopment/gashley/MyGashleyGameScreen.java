package blog.gamedevelopment.gashley;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import blog.gamedevelopment.gashley.core.GashleyGame;
import blog.gamedevelopment.gashley.core.components.B2dBodyComponent;
import blog.gamedevelopment.gashley.core.components.ControllableComponent;
import blog.gamedevelopment.gashley.core.components.TextureComponent;
import blog.gamedevelopment.gashley.core.components.TransformComponent;
import blog.gamedevelopment.gashley.core.listnerers.GashleyCollisionListener;
import blog.gamedevelopment.gashley.core.screens.GashleyGameScreen;
import blog.gamedevelopment.gashley.core.systems.ControlSystem;
import blog.gamedevelopment.gashley.core.utils.BodyFactory;
import blog.gamedevelopment.gashley.core.utils.DFUtils;

public class MyGashleyGameScreen extends GashleyGameScreen implements GashleyCollisionListener {
	
	public MyGashleyGameScreen(GashleyGame p) {
		super(p); // create world with normal gravity 0,-10f. For worlds with no gravity user super(p,0,0);

		//add a control system
		ControlSystem cs = new ControlSystem();
		cs.setController(this.controller);
		engine.addSystem(cs);
		
		makeDynamicSquareExample();
		makeStaticSquareExample();
		makeControlableCircleExample();
	}
	
	private void makeControlableCircleExample() {
		Entity ent = engine.createEntity();
		B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
		b2dbody.body = bodyFactory.makeCirclePolyBody(11, 11, 1f, BodyFactory.STONE, BodyType.DynamicBody);
		b2dbody.body.setUserData(ent);
		ent.add(b2dbody);
		
		TextureComponent texture = engine.createComponent(TextureComponent.class);
		texture.region = DFUtils.makeTextureRegion(16, 16, "000033");
		ent.add(texture);
		
		TransformComponent trans = engine.createComponent(TransformComponent.class);
		trans.position.set(11, 11, 0);
		ent.add(trans);
		
		ControllableComponent cont = engine.createComponent(ControllableComponent.class);
		cont.focused = true; // make this the object getting controlled
		cont.speed = 3f; // set the speed it moves
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
	private void makeStaticSquareExample(){
		Entity ent = engine.createEntity();
		B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
		b2dbody.body = bodyFactory.makeBoxPolyBody(32f, 1f, 50f, 1f, BodyFactory.STONE, BodyType.StaticBody);
		b2dbody.body.setUserData(ent);
		ent.add(b2dbody);
		
		TextureComponent texture = engine.createComponent(TextureComponent.class);
		texture.region = DFUtils.makeTextureRegion(50 * 16, 16, "330000");
		ent.add(texture);
		
		TransformComponent trans = engine.createComponent(TransformComponent.class);
		trans.position.set(32, 1f, 0);
		ent.add(trans);
		
		engine.addEntity(ent);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(controller);	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {		
	}

	@Override
	public void pause() {		
	}

	@Override
	public void resume() {
		Gdx.input.setInputProcessor(controller);	
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

}
