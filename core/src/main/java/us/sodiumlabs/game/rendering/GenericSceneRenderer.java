package us.sodiumlabs.game.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import us.sodiumlabs.game.acting.Actor;

import java.util.LinkedList;
import java.util.List;

public class GenericSceneRenderer implements SceneRenderer{
// ------------------------------ FIELDS ------------------------------

    private final List<Renderable> renderables = new LinkedList<>();

    private Color clearColor = new Color(0.375f, 0.375f, 0.375f, 1);

    private SpriteBatch batch;
    
    private Actor actor;

    private float x, y;

    private final float width, height;

    private static final float BORDER = 150;

    public GenericSceneRenderer(final float width, final float height) {
        this.width = width;
        this.height = height;
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
        if(actor.getX() + x < BORDER) { x = BORDER - actor.getX(); }
        else if(actor.getX() + x > width - BORDER) { x = width - BORDER - actor.getX(); }

        if(actor.getY() + y < BORDER) { y = BORDER - actor.getY(); }
        else if(actor.getY() + y > height - BORDER) { y = height - BORDER - actor.getY(); }

        batch.getProjectionMatrix().setTranslation( -x, -y, 0f);

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
