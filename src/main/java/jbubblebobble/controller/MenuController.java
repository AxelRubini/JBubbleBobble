package jbubblebobble.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import jbubblebobble.controller.applicationstate.HighScoreState;
import jbubblebobble.controller.applicationstate.PlayState;
import jbubblebobble.controller.applicationstate.ProfileState;


import java.io.IOException;

/**
 * Menu controller control the behavior of the view.
 */
public class MenuController {

   @FXML
   private Button btnPlay;

   @FXML
   private Button openProfile;

   @FXML
   private Button openHighScore;

   @FXML
   private Button closeApplication;


    /**
     * Operation to be performed when the view is initialized.
     * Set the action of the play button to start the game.
     * Set the action of the profile button to show the profile screen.
     * Set the action of the highscore button to show the highscore screen.
     * Set the action of the close button to close the application.
     *
     */
    @FXML
    public void initialize() {
        System.out.println(ApplicationContext.getInstance().getUser().getUsername());
        btnPlay.setOnAction(e -> {
            try {
                startGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

      openProfile.setOnAction(e -> {
          try {
              showProfile();
          } catch (IOException ex) {
              throw new RuntimeException(ex);
          }
      });

      openHighScore.setOnAction(e -> {
            try {
                showHighScore();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
      });

      closeApplication.setOnAction(e -> {
          Platform.exit();
      });
    }

    private void startGame() throws IOException {
        Stage stage = (Stage) btnPlay.getScene().getWindow();
        ApplicationContext.getInstance().setState(new PlayState(stage));
    }

    private void showProfile() throws IOException {
        Stage stage = (Stage) openProfile.getScene().getWindow();
        ApplicationContext.getInstance().setState(new ProfileState(stage));
    }

    private void showHighScore() throws IOException {
        Stage stage = (Stage) openHighScore.getScene().getWindow();
        ApplicationContext.getInstance().setState(new HighScoreState(stage));
    }




}
