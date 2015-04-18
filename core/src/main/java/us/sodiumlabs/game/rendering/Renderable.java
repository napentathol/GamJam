package us.sodiumlabs.game.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import us.sodiumlabs.game.utils.Createable;

public interface Renderable extends Createable {
    void render(final float offx, final float offy,
                final float delta, final SpriteBatch batch);
}
