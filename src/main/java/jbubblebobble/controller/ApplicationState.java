package jbubblebobble.controller;

import java.io.IOException;

/**
 *  Application state interface to be implemented by all the application states
 */
public interface ApplicationState {
    /**
     * Enter state.
     *
     * @throws IOException the io exception
     */
    void enterState() throws IOException;
}
