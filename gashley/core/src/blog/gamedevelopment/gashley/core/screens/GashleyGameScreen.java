package blog.gamedevelopment.gashley.core.screens;

import blog.gamedevelopment.gashley.core.GashleyGame;
import blog.gamedevelopment.gashley.core.input.GashleyBaseController;
import blog.gamedevelopment.gashley.core.input.KeyboardController;
import blog.gamedevelopment.gashley.core.listnerers.GashleyB2DContactListener;
import blog.gamedevelopment.gashley.core.systems.PhysicsDebugSystem;
import blog.gamedevelopment.gashley.core.systems.PhysicsSystem;
import blog.gamedevelopment.gashley.core.systems.RenderingSystem;
import blog.gamedevelopment.gashley.core.utils.BodyFactory;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GashleyGameScreen implements Screen {

	protected GashleyGame parent;
	protected PooledEngine engine;
	protected SpriteBatch sb;
	protected World world;
	protected RenderingSystem renderingSystem;
	protected PhysicsDebugSystem physicsDebugSystem;
	protected PhysicsSystem physicsSystem;
	protected GashleyBaseController controller;
	protected BodyFactory bodyFactory;
	protected TextureAtlas atlasGui;
	
	
	public GashleyGameScreen(GashleyGame p){
		this(p,0,-10f);
	}
	
	public GashleyGameScreen(GashleyGame p, float worldGravityX, float worldGravityY ){
		parent = p;
		init(worldGravityX,worldGravityY);
	}
	
	public void init(float worldGravityX, float worldGravityY){
		atlasGui = parent.assMan.manager.get("images/gui.atlas");
		sb = new SpriteBatch();
		engine = new PooledEngine();
		controller = new KeyboardController();
		world = new World(new Vector2(worldGravityX,worldGravityY), true);
		world.setContactListener(new GashleyB2DContactListener());
		bodyFactory = BodyFactory.getInstance(world);
		
		physicsSystem = new PhysicsSystem(world);
		engine.addSystem(physicsSystem);
		
		renderingSystem = new RenderingSystem(sb);
        engine.addSystem(renderingSystem);
        
        physicsDebugSystem = new PhysicsDebugSystem(world, renderingSystem.getCamera());
        engine.addSystem(physicsDebugSystem);

	}
	
	public World getBox2dWorld(){
		return this.world;
	}
	
	public void returnToMenu(){
		parent.changeScreen(GashleyGame.MENU);
	}
	
	public OrthographicCamera getCamera(){
		return this.renderingSystem.getCamera();
	}

	@Override
	public void dispose() {	
		sb.dispose();
		world.dispose();
	}
	
}
