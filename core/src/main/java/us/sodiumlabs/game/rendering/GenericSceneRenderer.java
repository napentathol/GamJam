package us.sodiumlabs.game.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import javafx.scene.Camera;
import us.sodiumlabs.game.acting.Actor;

import java.util.LinkedList;
import java.util.List;

public class GenericSceneRenderer
        implements SceneRenderer
{
// ------------------------------ FIELDS ------------------------------

    private final List<Renderable> renderables = new LinkedList<>();

    private final Viewport viewport;

    private Color clearColor = new Color(0.375f, 0.375f, 0.375f, 1);

    private SpriteBatch batch;
    
    private Actor actor;

    private float x, y;

    private static final float BORDER = 150;

    public GenericSceneRenderer(final Viewport viewport) {
        this.viewport = viewport;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Color getClearColor() {
        return clearColor;
    }

    public void setClearColor(final Color clearColor) {
        this.clearColor = clearColor;
    }

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface Renderable ---------------------

    @Override
    public void render(final float delta, final SpriteBatch batch) {
        viewport.getCamera().update();

        // Check Left Bound
        if(actor.getX() - x >  viewport.getScreenWidth()/2 - BORDER ) {
            x = actor.getX() + BORDER - viewport.getScreenWidth()/2;
        }

        // Check Right Bound
        else if (actor.getX() - x < BORDER - viewport.getScreenWidth()/2) {
            x = actor.getX() - BORDER + viewport.getScreenWidth()/2;
        }

        // Check Upper Bound
        if(actor.getY() - y > viewport.getScreenHeight()/2 - BORDER) {
            y = actor.getY() + BORDER - viewport.getScreenHeight()/2;
        }

        // Check Lower Bound
        else if (actor.getY() - y < BORDER - viewport.getScreenHeight()/2) {
            y = actor.getY() - BORDER + viewport.getScreenHeight()/2;
        }

        // Update camera
        viewport.getCamera().position.x = x;
        viewport.getCamera().position.y = y;
        batch.setProjectionMatrix(viewport.getCamera().combined);

        // Render with 0 offset.
        render(0, 0, delta, batch);
    }

// --------------------- Interface SceneRenderer ---------------------

    @Override
    public void render(final float delta){
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        render(delta, batch);
        batch.end();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderables.stream().forEach(Renderable::create);
    }

    @Override
    public void dispose() {
        batch.dispose();
        renderables.stream().forEach(Renderable::dispose);
    }

    @Override
    public void render(float offx, float offy, float delta, final SpriteBatch batch) {
        renderables.stream()
                .forEach((r) -> r.render(offx, offy, delta, batch));
    }

    @Override
    public void addRenderable(final Renderable renderable) {
        renderables.add(renderable);
    }

    @Override
    public void removeRenderable(final Renderable renderable) {
        renderables.remove(renderable);
    }

    @Override
    public void follow(final Actor actor) {
        this.actor = actor;
    }
}
