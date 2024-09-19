package jbubblebobble.controller.applicationstate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jbubblebobble.controller.ApplicationState;

/**
 * Loose state.
 */
public class LoseState implements ApplicationState {
    private Stage stage;

    /**
     * Instantiates a new Lose state.
     *
     * @param stage the stage
     */
    public LoseState(Stage stage){
        this.stage = stage;
    }
    /**
     * Enter state display the loose screen and  initialize the loose controller.
     */
    @Override
    public void enterState() {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoseScreen.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
