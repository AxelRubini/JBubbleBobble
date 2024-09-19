package jbubblebobble.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jbubblebobble.controller.applicationstate.ProfileState;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Select avatar controller control the Select Avatar screen.
 */
public class SelectAvatarController {

    @FXML
    private Button menu;

    @FXML
    private ImageView avatarPreView;

    @FXML
    private ComboBox<String> comboBox;



    private String actualAvatar;

    /**
     * Operation to be performed when the view is initialized.
     * Set the actual avatar and the preview of the avatar.
     * Set the action of the menu button to update the user avatar and return to the profile screen.
     * @throws URISyntaxException the uri syntax exception
     * @throws RuntimeException the runtime exception
     *
     */

    @FXML
    public void initialize() throws URISyntaxException {
        ApplicationContext applicationContext= ApplicationContext.getInstance();
        comboBox.getItems().addAll("default","avatar1","avatar2");
        actualAvatar = applicationContext.getUser().getAvatarPath();
        comboBox.setValue(actualAvatar);
        avatarPreView.setImage(new Image(getClass().getResource(applicationContext.getAvatar(actualAvatar)).toURI().toString()));
        comboBox.setOnAction(e -> {
            actualAvatar = comboBox.getValue();
            try {
                avatarPreView.setImage(new Image(getClass().getResource(applicationContext.getAvatar(actualAvatar)).toURI().toString()));
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
        menu.setOnAction(e -> {
            Stage stage = (Stage) menu.getScene().getWindow();
            applicationContext.getUser().setAvatarPath(actualAvatar);
            applicationContext.getUserManager().updateUser(applicationContext.getUser());
            try {
                ApplicationContext.getInstance().setState(new ProfileState(stage));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
