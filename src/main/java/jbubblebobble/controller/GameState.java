package jbubblebobble.controller;

import java.io.IOException;

/**
 * Game state interface to be implemented by all the game states
 * The game states handle the behavior of the game in different states
 */
public interface GameState {
    /**
     * Enter state.
     *
     * @throws IOException the io exception
     */
    void enterState() throws IOException;

    /**
     * Exit state.
     */
    void exitState();
}
