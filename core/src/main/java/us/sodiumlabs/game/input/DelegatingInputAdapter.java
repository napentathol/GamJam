package us.sodiumlabs.game.input;

import com.badlogic.gdx.InputAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for sending key inputs to multiple adapters.
 */
public class DelegatingInputAdapter extends InputAdapter {
    private final List<InputAdapter> inputAdapters = new ArrayList<>();

    public boolean keyUp(final int keyCode) {
        for(final InputAdapter adapter : inputAdapters) {
            if(adapter.keyUp(keyCode)){
                return true;
            }
        }

        return false;
    }

    public boolean keyDown(final int keyCode) {
        for(final InputAdapter adapter : inputAdapters) {
            if(adapter.keyDown(keyCode)){
                return true;
            }
        }

        return false;
    }

    /**
     * Add an input adapter to receive key inputs.
     *
     * @param adapter an adapter to receive key inputs.
     * @return true (as specified by {@link java.util.Collection#add}).
     */
    public boolean addInputAdapter(final InputAdapter adapter) {
        return inputAdapters.add(adapter);
    }

    /**
     * Remove an input adapter that is receiving key inputs.
     *
     * @param adapter an adapter receiving key inputs.
     * @return true (as specified by {@link java.util.Collection#remove}).
     */
    public boolean removeInputAdapter(final InputAdapter adapter) {
        return inputAdapters.remove(adapter);
    }

    /**
     * Clear all input adapters.
     */
    public void clearInputAdapters() {
        inputAdapters.clear();
    }
}
