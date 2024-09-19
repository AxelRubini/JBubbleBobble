package jbubblebobble.controller.gamestate;

import jbubblebobble.controller.GameManager;
import jbubblebobble.controller.GameState;

/**
 * Pause state.
 */
public class PauseState implements GameState {
    /**
     * Stop the game loop.
     */
    @Override
    public void enterState() {
        GameManager.getInstance().getLevelController().stopGameLoop();
    }

    @Override
    public void exitState() {
    }
}
