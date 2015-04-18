package us.sodiumlabs.game.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import us.sodiumlabs.game.acting.Actor;

public interface SceneRenderer extends Renderable {
    void render(final float delta);

    void render(final float delta, final SpriteBatch batch);

    void addRenderable(final Renderable renderable);

    void removeRenderable(final Renderable renderable);

    void follow(final Actor actor);
}
