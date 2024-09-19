package jbubblebobble.controller.applicationstate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jbubblebobble.controller.ApplicationState;

/**
 * Select avatar state  in this state the user can select an avatar.
 */
public class SelectAvatarState implements ApplicationState {
    private Stage stage;

    /**
     * Instantiates a new Select avatar state.
     *
     * @param stage the stage
     */
    public SelectAvatarState(Stage stage) {
        this.stage = stage;
    }
    /**
     * Enter state performe the update of the user and display the select avatar screen and  initialize the select avatar controller.
     */
    @Override
    public void enterState() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectAvatarScreen.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
