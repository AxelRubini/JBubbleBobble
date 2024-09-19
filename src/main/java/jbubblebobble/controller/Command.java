package jbubblebobble.controller;

import java.io.IOException;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute.
     *
     * @throws IOException the io exception
     */
    void execute() throws IOException;
}
