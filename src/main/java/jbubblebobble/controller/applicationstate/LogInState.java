package jbubblebobble.controller.applicationstate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jbubblebobble.controller.ApplicationState;

import java.io.IOException;

/**
 * Log in state.
 */
public class LogInState implements ApplicationState {
    private Stage stage;

    /**
     * Instantiates a new Log in state.
     *
     * @param stage the stage
     * @throws IOException the io exception
     */
    public LogInState(Stage stage) throws IOException {
        this.stage = stage;
    }
    /**
     * Enter state display the log in screen and  initialize the log in controller.
     */
    @Override
    public void enterState() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogInScreen.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
