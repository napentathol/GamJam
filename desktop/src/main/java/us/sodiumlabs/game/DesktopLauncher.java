package us.sodiumlabs.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import us.sodiumlabs.game.application.Application;

public class DesktopLauncher {
    public static void main (final String arg[]) {
        // Application logic here
        final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;

        new LwjglApplication(new Application());
    }
}
