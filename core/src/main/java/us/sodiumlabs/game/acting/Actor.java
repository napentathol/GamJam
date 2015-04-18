package us.sodiumlabs.game.acting;

import us.sodiumlabs.game.input.GameInputProvider;

public interface Actor {
    public void act(final float delta);
    public void acceptInputProvider(final GameInputProvider inputProvider);

    public float getX();
    public float getY();

    /**
     * Get the angle in radians.
     *
     * @return the angle in radians.
     */
    public float getRotation();

}
