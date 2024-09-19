package jbubblebobble.controller.command;

import jbubblebobble.controller.Command;
import jbubblebobble.controller.GameManager;

import java.io.IOException;

/**
 * Pause command.
 */
public class PauseCommand implements Command {

    @Override
    public void execute() throws IOException {
        GameManager.getInstance().pauseGame();

    }
}
