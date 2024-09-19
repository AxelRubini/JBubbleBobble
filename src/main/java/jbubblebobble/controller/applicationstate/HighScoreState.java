package jbubblebobble.controller.applicationstate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jbubblebobble.controller.ApplicationContext;
import jbubblebobble.controller.ApplicationState;

import java.io.IOException;

/**
 * High score state.
 */
public class HighScoreState implements ApplicationState {
    private Stage stage;

    /**
     * Instantiates a new High score state.
     *
     * @param stage the stage
     */
    public HighScoreState(Stage stage) {
        this.stage = stage;
    }
    /**
     * Enter state display the high score screen and  initialize the high score controller.
     */
    @Override
    public void enterState() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HighScoreScreen.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
