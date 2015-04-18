package us.sodiumlabs.game.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background implements Renderable {
    private final Texture image;

    public Background(final String name) {
        this.image = new Texture(name);
        image.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    @Override
    public void create() { }

    @Override
    public void dispose() { }

    @Override
    public void render(final float offx, final float offy,
                       final float delta, final SpriteBatch batch)
    {
        int tileCountY = 62;
        int tileCountX = 82;
        batch.draw(image,
                offx % image.getWidth() - image.getWidth(),
                offy % image.getHeight() - image.getHeight(),
                image.getWidth() * tileCountX,
                image.getHeight() * tileCountY,
                0, tileCountY, tileCountX, 0);
    }
}
