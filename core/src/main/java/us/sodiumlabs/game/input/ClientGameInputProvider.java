package us.sodiumlabs.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ClientGameInputProvider
        extends InputAdapter
        implements GameInputProvider
{
    private final Viewport viewport;

    private boolean left, right, throttle, brake;

    private int leftIn = Input.Keys.A,
            rightIn = Input.Keys.D,
            upIn = Input.Keys.W,
            backIn = Input.Keys.S;

    public ClientGameInputProvider(final Viewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(upIn == keycode) {
            throttle = false;
        }
        if(backIn == keycode) {
            brake = false;
        }
        if(leftIn == keycode) {
            left = false;
        }
        if(rightIn == keycode) {
            right = false;
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(upIn == keycode) {
            throttle = true;
        }
        if(backIn == keycode) {
            brake = true;
        }
        if(leftIn == keycode) {
            left = true;
        }
        if(rightIn == keycode) {
            right = true;
        }

        if(Input.Keys.SPACE == keycode) {
            System.exit(0);
        }

        return false;
    }

    @Override
    public boolean isLeft() {
        return left && !right;
    }

    @Override
    public boolean isRight() {
        return right && !left;
    }

    @Override
    public boolean isBack() {
        return throttle && !brake;
    }

    @Override
    public boolean isForward() {
        return brake && !throttle;
    }

    @Override
    public Vector2 getMouseVector() {
        return new Vector2(
            Gdx.input.getX() - viewport.getScreenWidth() / 2,
            Gdx.input.getY() - viewport.getScreenHeight() / 2
        );
    }

    @Override
    public Vector2 getCameraAdjMouseVector() {
        System.out.println("Mouse: " + getMouseVector().x +
                "\t\tCAM: " + viewport.getCamera().position.x);

        return getMouseVector().sub(getCameraVector());
    }

    private Vector2 getCameraVector() {
        return new Vector2( viewport.getCamera().position.x, viewport.getCamera().position.y );
    }
}
