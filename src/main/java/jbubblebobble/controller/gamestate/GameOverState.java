package jbubblebobble.controller.gamestate;

import jbubblebobble.controller.GameManager;
import jbubblebobble.controller.GameState;

import java.io.IOException;

/**
 * Game over state.
 */
public class GameOverState implements GameState {
    /**
     * Reset the game by calling the GameManager resetGame method.
     *
     * @throws IOException the io exception
     */
    @Override
    public void enterState() throws IOException {
        GameManager.getInstance().resetGame();
    }
    @Override
    public void exitState() {
    }
}
