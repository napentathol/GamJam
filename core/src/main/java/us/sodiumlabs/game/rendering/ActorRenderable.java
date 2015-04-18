package us.sodiumlabs.game.rendering;

import us.sodiumlabs.game.acting.Actor;

public interface ActorRenderable extends Renderable {
    void addActor(final Actor actor);

    void removeActor(final Actor actor);
}
