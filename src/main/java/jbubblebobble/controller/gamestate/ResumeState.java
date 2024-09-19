package jbubblebobble.controller.gamestate;

import jbubblebobble.controller.GameManager;
import jbubblebobble.controller.GameState;

/**
 * Resume state.
 */
public class ResumeState implements GameState {
    /**
     * Restart the game loop.
     */
    @Override
    public void enterState() {
        GameManager.getInstance().getLevelController().restartGameLoop();
    }

    @Override
    public void exitState() {
    }
}
