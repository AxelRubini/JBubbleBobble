package jbubblebobble.controller;

import javafx.stage.Stage;
import jbubblebobble.controller.applicationstate.LoseState;
import jbubblebobble.controller.applicationstate.WinState;
import jbubblebobble.controller.gamestate.GameOverState;
import jbubblebobble.controller.gamestate.PauseState;
import jbubblebobble.controller.gamestate.ResumeState;
import jbubblebobble.controller.gamestate.StartState;
import jbubblebobble.view.LevelView;
import lombok.Getter;
import lombok.Setter;
import utility.Config;

import java.io.IOException;

/**
 * GameManager class used to handle the state of the game.
 * The game manager is a singleton class that is used to manage the state of the game.
 * The game manager is used to change the state of the game and to change the level of the game.
 *
 */
public class GameManager {
    private static GameManager instance;
    private int activeLevel = 1;
    private GameState state;
    private LevelController levelController;
    private LevelView levelView;
    private Stage stage;

    private GameManager() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }


    /**
     * Start game.
     *
     * @param stage the stage
     * @throws IOException the io exception
     */
    public void startGame(Stage stage) throws IOException{
        setState(new StartState(stage));
    }

    /**
     * Change level.
     *
     * @param stage the stage
     * @return
     * @throws IOException the io exception
     */
    public boolean changeLevel(Stage stage) throws IOException {
        activeLevel++;
        if (activeLevel > Config.NUM_LEVELS) {
            endGame(true);
            AudioManager.getInstance().stopTheme(); // stop theme music
            return true;
        }
        setState(new StartState(stage));
        return false;
    }

    /**
     * Pause game.
     *
     * @throws IOException the io exception
     */
    public void pauseGame() throws IOException {
        setState(new PauseState());
    }

    /**
     * Resume game.
     *
     * @throws IOException the io exception
     */
    public void resumeGame() throws IOException {
        setState(new ResumeState());
    }

    /**
     * End game.
     */
    public void endGame(boolean win) throws IOException {
        if (win){
            ApplicationContext.getInstance().setState(new WinState(stage));
        }else{
            ApplicationContext.getInstance().setState(new LoseState(stage));
        }
        setState(new GameOverState());


    }

    /**
     * Get path string.
     *
     * @return the string
     */
    public String getPath(){
        return Config.PATH_TO_MAP + "lvl" + activeLevel + ".txt";
    }

    /**
     * Sets state.
     *
     * @param state the state
     * @throws IOException the io exception
     */
    public void setState(GameState state) throws IOException {
        if (this.state != null) {
            this.state.exitState();
        }
        this.state = state;
        state.enterState();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
    /**
     * Game manager instance is reset to null.
     * in this way the game manager is reset and the game can be started again
     * from the beginning.
     *
     * @throws IOException the io exception
     */
    public void resetGame() throws IOException {
        this.instance = null;
    }

    public LevelController getLevelController() {
        return levelController;
    }

    public void setLevelController(LevelController levelController) {
        this.levelController = levelController;
    }

    public LevelView getLevelView() {
        return levelView;
    }

    public void setLevelView(LevelView levelView) {
        this.levelView = levelView;
    }
}
