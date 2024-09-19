package jbubblebobble.controller.applicationstate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jbubblebobble.controller.ApplicationContext;
import jbubblebobble.controller.ApplicationState;
import jbubblebobble.model.user.User;

/**
 * Win state in this state the user has won the game and the win screen is displayed.
 */
public class WinState implements ApplicationState {
    private Stage stage;

    /**
     * Instantiates a new Win state.
     *
     * @param stage the stage
     */
    public WinState(Stage stage){
        this.stage = stage;
    }
    /**
     * Enter state performe the update of the user and display the win screen and  initialize the win controller.
     */
    @Override
    public void enterState() {
        User user = ApplicationContext.getInstance().getUser();
        user.setWonGames(user.getWonGames() + 1);
        ApplicationContext.getInstance().getUserManager().updateUser(user);
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WinScreen.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
