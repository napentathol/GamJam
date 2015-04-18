package us.sodiumlabs.game.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import us.sodiumlabs.game.acting.Actor;

import java.util.LinkedList;
import java.util.List;

public class GenericActorRenderable implements ActorRenderable {
// ------------------------------ FIELDS ------------------------------

    private final Texture image;
    
    private final float width, height, halfWidth, halfHeight;

    private float offsetRotation;

    private final List<Actor> actorsToRender = new LinkedList<>();

// --------------------------- CONSTRUCTORS ---------------------------

    public GenericActorRenderable(final String imageName) {
        image = new Texture(imageName);

        width = image.getWidth();
        height = image.getHeight();
        halfWidth = width / 2f;
        halfHeight = height / 2f;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public float getOffsetRotation() {
        return offsetRotation;
    }

    public void setOffsetRotation(float offsetRotation) {
        this.offsetRotation = offsetRotation;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface ActorRenderable ---------------------

    @Override
    public void addActor(final Actor actor) {
        actorsToRender.add(actor);
    }

    @Override
    public void removeActor(final Actor actor) {
        actorsToRender.remove(actor);
    }

// --------------------- Interface Renderable ---------------------


    @Override
    public void create() { }

    @Override
    public void dispose() { }

    @Override
    public void render(final float offx, final float offy,
                       final float delta, final SpriteBatch batch)
    {
        actorsToRender.stream().forEach(
//              a -> batch.draw(image, a.getX(), a.getY())
            a -> batch.draw(image,
                a.getX() + offx - halfWidth,
                a.getY() + offy - halfHeight,
                halfWidth,
                halfHeight,
                width,
                height,
                1,
                1,
                (float) Math.toDegrees(a.getRotation()) + getOffsetRotation(),
                0, 0,
                image.getWidth(), image.getHeight(),
                //1, 1,
                false, false
            )
        );
    }
}
