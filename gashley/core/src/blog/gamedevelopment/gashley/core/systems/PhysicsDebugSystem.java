package blog.gamedevelopment.gashley.core.systems;

import blog.gamedevelopment.gashley.core.GashleyBox2dRenderer;
import blog.gamedevelopment.gashley.settings.MainConfig;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;


public class PhysicsDebugSystem extends IteratingSystem {

    private GashleyBox2dRenderer debugRenderer;
    private World world;
    private OrthographicCamera camera;

    @SuppressWarnings("unchecked")
	public PhysicsDebugSystem(World world, OrthographicCamera camera){
        super(Family.all().get());
        debugRenderer = new GashleyBox2dRenderer();
        this.world = world;
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (MainConfig.DEBUG_RENDER_BOX2D) debugRenderer.render(world, camera.combined);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
