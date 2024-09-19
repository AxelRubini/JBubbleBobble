package jbubblebobble.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jbubblebobble.controller.applicationstate.MainMenuState;
import jbubblebobble.model.user.UserManager;

import java.io.IOException;

/**
 * The type Log in controller.
 */
public class LogInController {

    @FXML
    private PasswordField passwordBox;

    @FXML
    private TextField userNameBox;

    @FXML
    private Button loginButton;

    @FXML
    private Text errorText;

    private UserManager userManager = new UserManager();

    /**
     * Operation to be performed when the view is initialized.
     * Set the action of the login button to authenticate the user.
     */
    @FXML
    public void initialize() {
        loginButton.setOnAction(e -> {
            try {
                handleLogin();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Handle login method to authenticate the user.
     * return to the main menu if the user is authenticated.
     *
     * @throws IOException the io exception
     */
    private void handleLogin() throws IOException {
        String userName = userNameBox.getText();
        String password = passwordBox.getText();

        if (userManager.authenticate(userName, password)) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            ApplicationContext.getInstance().setUserManager(userManager);
            ApplicationContext.getInstance().setUser(userManager.getUser(userName));
            ApplicationContext.getInstance().setState(new MainMenuState(stage));
            System.out.println(ApplicationContext.getInstance().getUser().getUsername());
        }else {
            errorText.setText("username already used or wrong password");
        }
    }

}
