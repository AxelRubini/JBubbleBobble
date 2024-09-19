package jbubblebobble.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jbubblebobble.controller.applicationstate.MainMenuState;

import java.io.IOException;
import java.util.Objects;

/**
 *  Lose controller control the behavior of the view.
 */
public class LoseController {

    @FXML
    private ImageView LoseView;

    /**
     * Operation to be performed when the view is initialized.
     * Set the focus on the view and set the action of the view to return to the main menu when the enter key is pressed
     *
     *
     */
    @FXML
    public void initialize() {
        LoseView.setFocusTraversable(true);
        LoseView.setOnKeyPressed(this::handleKeyPress);
        LoseView.requestFocus();
    }


    private void handleKeyPress(KeyEvent keyEvent) {
        if (Objects.requireNonNull(keyEvent.getCode()) == KeyCode.ENTER) {
            try {
                returnToMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Return to menu.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void returnToMenu() throws IOException {
        Stage stage = (Stage)  LoseView.getScene().getWindow();
        ApplicationContext.getInstance().setState(new MainMenuState(stage));
    }

}
