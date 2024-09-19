package jbubblebobble.controller.applicationstate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jbubblebobble.controller.ApplicationState;

import java.io.IOException;

/**
 * The type Profile state.
 */
public class ProfileState implements ApplicationState {
    private Stage stage;

    /**
     * Instantiates a new Profile state.
     *
     * @param stage the stage
     */
    public ProfileState(Stage stage) {
        this.stage = stage;
    }
    /**
     * Enter state perform the loading of the user and display the profile screen and  initialize the profile controller.
     */
    @Override
    public void enterState( ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfileScreen.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
