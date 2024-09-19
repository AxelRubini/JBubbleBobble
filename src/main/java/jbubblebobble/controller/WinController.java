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
 * The type Win controller.
 */
public class WinController {

    @FXML
    private ImageView winView;

    /**
     * This method is called when the view is initialized
     * Set the focus on the view and set the action of the view to return to the main menu when the enter key is pressed
     *
     */
    @FXML
    public void initialize() {
        winView.setFocusTraversable(true);
        winView.setOnKeyPressed(this::handleKeyPress);
        winView.requestFocus();
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
        Stage stage = (Stage) winView.getScene().getWindow();
        ApplicationContext.getInstance().setState(new MainMenuState(stage));
    }
}
