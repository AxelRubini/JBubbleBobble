package jbubblebobble.controller.gamestate;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jbubblebobble.controller.AudioManager;
import jbubblebobble.controller.GameManager;
import jbubblebobble.controller.GameState;
import jbubblebobble.controller.LevelController;
import jbubblebobble.model.level.Level;
import jbubblebobble.model.level.LevelFactory;
import jbubblebobble.view.LevelView;

import java.io.IOException;

/**
 * The type Start state.
 */
public class StartState implements GameState {
    private Stage stage;

    /**
     * Instantiates a new Start state.
     *
     * @param stage the stage
     */
    public StartState(Stage stage) {
        this.stage = stage;
    }

    /**
     * In this method, the level is created from the file and the level view is created.
     * and the game start with the level controller.
     *
     * @throws IOException the io exception
     */
    @Override
    public void enterState() throws IOException {
        Level level = LevelFactory.createLevelFromFile(GameManager.getInstance().getPath());
        LevelView levelView = new LevelView();
        LevelController levelController = new LevelController(level, levelView);
        GameManager.getInstance().setLevelController(levelController);
        GameManager.getInstance().setStage(stage);
        Parent root = new Pane();
        root.setStyle("-fx-background-color: black;");
        ((Pane) root).getChildren().add(levelView);
        HBox textBox = new HBox();
        textBox.setLayoutY(20);
        textBox.setLayoutX(20);
        textBox.getChildren().add(levelView.getScoreLabel());
        HBox lifeBox = new HBox();
        lifeBox.setLayoutY(20);
        lifeBox.setLayoutX(200);
        lifeBox.getChildren().add(levelView.getLifesLabel());
        ((Pane) root).getChildren().add(lifeBox);
        ((Pane) root).getChildren().add(textBox);
        Scene scene = new Scene(root);
        levelView.render(level.createLevelMap());
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void exitState() {
    }


}
