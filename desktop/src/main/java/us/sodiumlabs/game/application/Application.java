package us.sodiumlabs.game.application;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import us.sodiumlabs.game.utils.Display;

public class Application
    extends ApplicationAdapter
{
    private Display display;

    @Override
    public void create() {
        super.create();

        display = new GamJam();
        display.create();
    }

    @Override
    public void render() {
        super.render();

        final float delta = Gdx.graphics.getDeltaTime();

        display.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();

        display.dispose();
    }
}
