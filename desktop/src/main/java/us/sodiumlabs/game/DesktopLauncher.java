package us.sodiumlabs.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import us.sodiumlabs.game.application.Application;
import us.sodiumlabs.game.utils.Constants;

public class DesktopLauncher {
    public static void main (final String arg[]) {
        // Application logic here
        final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Constants.START_WIDTH;
        config.height = Constants.START_HEIGHT;

        new LwjglApplication(new Application());
    }
}
