package jbubblebobble.controller.applicationstate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jbubblebobble.controller.ApplicationState;

/**
 * The type Main menu state.
 */
public class MainMenuState implements ApplicationState {
    private Stage stage;

    /**
     * Instantiates a new Main menu state.
     *
     * @param stage the stage
     */
    public MainMenuState(Stage stage){
        this.stage = stage;
    }
    /**
     * Enter state display the main menu screen and  initialize the main menu controller.
     */
    @Override
    public void enterState() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuBubbleBobble.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            System.out.println("some");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
