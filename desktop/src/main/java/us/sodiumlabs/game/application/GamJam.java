package us.sodiumlabs.game.application;

import com.badlogic.gdx.Gdx;
import us.sodiumlabs.game.acting.GuyActor;
import us.sodiumlabs.game.input.ClientGameInputProvider;
import us.sodiumlabs.game.input.DelegatingInputAdapter;
import us.sodiumlabs.game.rendering.GenericActorRenderable;
import us.sodiumlabs.game.utils.Display;

public class GamJam
    extends Display
{

    @Override
    public void create() {
        super.create();

        final ClientGameInputProvider wasdClientProvider = new ClientGameInputProvider();

        final DelegatingInputAdapter delegatingInputAdapter = new DelegatingInputAdapter();
        delegatingInputAdapter.addInputAdapter(wasdClientProvider);
        Gdx.input.setInputProcessor(delegatingInputAdapter);

        final GuyActor playerActor = new GuyActor(getWorld());
        playerActor.acceptInputProvider(wasdClientProvider);
        getActors().add(playerActor);

        final GenericActorRenderable renderable =
                new GenericActorRenderable("DevGunGuy.png");
        renderable.setOffsetRotation(-90);
        renderable.addActor(playerActor);

        getRenderer().addRenderable(renderable);
        getRenderer().follow(playerActor);
    }

    @Override
    public void dispose() {
        getRenderer().dispose();
        getWorld().dispose();
    }
}
