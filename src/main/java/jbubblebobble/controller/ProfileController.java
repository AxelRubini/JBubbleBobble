package jbubblebobble.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jbubblebobble.controller.applicationstate.MainMenuState;
import jbubblebobble.controller.applicationstate.SelectAvatarState;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Profile controller  control the behavior of the view.
 */
public class ProfileController {

    @FXML
    private Label name;

    @FXML
    private Label  games ;

    @FXML
    private Label  highscore ;

    @FXML
    private Label lostGame;

    @FXML
    private Label wonGame;

    @FXML
    private Button menu;

    @FXML
    private ImageView avatar;

    @FXML
    private Label changeAvatar;

    /**
     * Operation to be performed when the view is initialized.
     * Set the user information in the view.
     * Set the action of the change avatar button to go to the select avatar screen.
     *
     * @throws URISyntaxException the uri syntax exception
     */
    @FXML
   public void initialize() throws URISyntaxException {
       System.out.println(ApplicationContext.getInstance().getUser().getUsername());
        name.setText(ApplicationContext.getInstance().getUser().getUsername());
        games.setText("Games played: " + ApplicationContext.getInstance().getUser().getGamesPlayed());
        highscore.setText("Highscore: " + ApplicationContext.getInstance().getUser().getHighScore());
        lostGame.setText("Lost games: " + ApplicationContext.getInstance().getUser().getLostGames());
        wonGame.setText("Won games: " + ApplicationContext.getInstance().getUser().getWonGames());
        avatar.setImage(new Image(getClass().getResource(ApplicationContext.getInstance()
                .getAvatar(ApplicationContext.getInstance().getUser().getAvatarPath()))
                .toURI().toString()));
        changeAvatar.setOnMouseClicked(e -> {
            Stage stage = (Stage) menu.getScene().getWindow();
            try {
                ApplicationContext.getInstance().setState(new SelectAvatarState(stage));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
   }
    @FXML
    private void handleMenu() throws IOException {
        Stage stage = (Stage) name.getScene().getWindow();
        ApplicationContext.getInstance().setState(new MainMenuState(stage));
    }


}
