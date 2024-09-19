package jbubblebobble.controller.command;

import jbubblebobble.controller.Command;
import jbubblebobble.controller.GameManager;

import java.io.IOException;

/**
 * Resume command.
 */
public class ResumeCommand implements Command {

    @Override
    public void execute() throws IOException {
        GameManager.getInstance().resumeGame();
    }
}
