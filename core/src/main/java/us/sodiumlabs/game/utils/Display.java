package us.sodiumlabs.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import us.sodiumlabs.game.acting.Actor;
import us.sodiumlabs.game.rendering.GenericSceneRenderer;
import us.sodiumlabs.game.rendering.SceneRenderer;

import java.util.LinkedList;
import java.util.List;

public abstract class Display
    implements Createable
{
    private List<Actor> actors = new LinkedList<>();

    private SceneRenderer renderer = new GenericSceneRenderer(800, 600);

    private World world;

    private Box2DDebugRenderer debug = new Box2DDebugRenderer();

    public void create() {
        world = new World(new Vector2(0,0), true);
        renderer.create();

    }

    public void render(float delta) {
        // Tell the actors to act.
        for(final Actor actor : actors) {
            actor.act(delta);
        }

        // Do physics!
        if(!world.isLocked()) {
            world.step(delta, 1, 1);
        }

        // Render the scene.
        renderer.render(delta);

        debug.render(world, (new SpriteBatch()).getProjectionMatrix());
    }

    public World getWorld() {
        return world;
    }

    public SceneRenderer getRenderer() {
        return renderer;
    }

    public List<Actor> getActors() {
        return actors;
    }
}
