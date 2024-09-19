package jbubblebobble.controller.applicationstate;

import javafx.stage.Stage;
import jbubblebobble.controller.ApplicationState;
import jbubblebobble.controller.AudioManager;
import jbubblebobble.controller.GameManager;

import java.io.IOException;

/**
 *  Play state.
 */
public class PlayState implements ApplicationState {
    private Stage Stage;

    /**
     * Instantiates a new Play state.
     *
     * @param stage the stage
     */
    public PlayState(Stage stage) {
        this.Stage = stage;
    }

    /**
     * Enter state create a game instance and set the game manager to the start state.
     */
    @Override
    public void enterState() throws IOException {
        GameManager.getInstance().startGame(Stage);
        AudioManager.getInstance().playTheme("src/main/resources/sounds/01-Introduction-_-Main-Theme.wav");
    }


}
