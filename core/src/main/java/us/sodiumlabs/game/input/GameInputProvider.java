package us.sodiumlabs.game.input;

import com.badlogic.gdx.math.Vector2;

public interface GameInputProvider {
    boolean isLeft();
    boolean isRight();
    boolean isForward();
    boolean isBack();

    Vector2 getMouseVector();

    Vector2 getCameraAdjMouseVector();
}
