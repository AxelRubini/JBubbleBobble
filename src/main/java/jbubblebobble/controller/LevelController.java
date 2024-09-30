package jbubblebobble.controller;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import jbubblebobble.controller.command.*;
import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.characters.Enemy;
import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.level.Level;
import jbubblebobble.model.user.User;
import jbubblebobble.view.LevelView;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.SneakyThrows;
import utility.Config;
import javafx.application.Platform;
import java.io.IOException;
import java.util.List;

/**
 * Level controller handle the game loop and the user input to control the player.
 */
public class LevelController {

    private Level level;
    private LevelView levelView;
    private Command moveLeftCommand;
    private Command moveRightCommand;
    private Command jumpCommand;
    private Command fireCommand;
    private Command pauseCommand;
    private Command resumeCommand;
    private boolean[] keys = new boolean[256];
    private static final double UPDATE_INTERVAL = 0.008;
    private AnimationTimer timer;
    private long levelStartTime;

    private boolean isActive = true;

    /**
     * Instantiates a new Level controller.
     *
     * @param level     the level
     * @param levelView the level view
     */
    public LevelController(Level level, LevelView levelView) {
        this.level = level;
        this.levelView = levelView;
        Player player = level.getPlayer();
        this.moveLeftCommand = new MoveLeftCommand(player);
        this.moveRightCommand = new MoveRightCommand(player);
        this.jumpCommand = new JumpCommand(player);
        this.fireCommand = new FireCommand(player);
        this.pauseCommand = new PauseCommand();
        this.resumeCommand = new ResumeCommand();
        this.levelStartTime = System.currentTimeMillis();
        init();
    }
    private void init(){
        level.addObserver(levelView);
        level.getPlayer().addObserver(AudioManager.getInstance());
        startGameLoop();
        setupKeyHandlers();
    }
    /**
     * Game loop update the game state.
     * check for end of level and end of game
     */
    private void startGameLoop() {
        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }

                double elapsedTime = (now - lastUpdate) / 1_000_000_000.0;
                if (elapsedTime >= UPDATE_INTERVAL) {
                    try {
                        if (System.currentTimeMillis() - levelStartTime > 3000) {
                            updateGame(elapsedTime);
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    lastUpdate = now;
                }
                if (isActive) {
                    checkEndOfLevel();
                }
                try {
                    checkEndOfGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.start();
    }

    private void checkEndOfGame() throws IOException {
        if (level.getPlayer().getLives() == 0) {
            saveUser();
            level.getPlayer().reset();
            AudioManager.getInstance().stopTheme();
            GameManager.getInstance().endGame(false);
            AudioManager.getInstance().play(Config.PATH_TO_AUDIO_GAME_OVER);
            level.resetLevel();

            timer.stop();
        }
    }



    private void checkEndOfLevel() {
        List<Entity> t = level.getEntitiesCollection().getEntities();
        if (t.stream().noneMatch(entity -> entity instanceof Enemy && isActive)) {
            isActive = false;
            Stage stage = (Stage) levelView.getScene().getWindow();
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> {
                try {
                    saveUser();
                    if (GameManager.getInstance().changeLevel(stage)) {
                        level.resetLevel();
                        resetController();
                        timer.stop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                timer.stop();
            });
            delay.play();
        }
    }

    private void saveUser() throws IOException {
        User user = ApplicationContext.getInstance().getUser();
        user.setHighScore(level.getPlayer().getScore());
        user.incrementGamesPlayed();
        user.setWonGames(user.getWonGames() + 1);
        ApplicationContext.getInstance().getUserManager().updateUser(user);
    }

    @SneakyThrows
    private void setupKeyHandlers() {
        levelView.setOnKeyPressed(this::handleKeyPressed);
        levelView.setOnKeyReleased(this::handleKeyReleased);
    }

    private void handleKeyPressed(KeyEvent event) {
        keys[event.getCode().getCode()] = true;
        if (event.getCode() == KeyCode.P) {
            try {
                pauseCommand.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getCode() == KeyCode.R) {
            try {
                resumeCommand.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleKeyReleased(KeyEvent event) {
        keys[event.getCode().getCode()] = false;
        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
            level.getPlayer().stopMoving();
        }
    }
    /**
     * Update entities.
     */
    private void updateEntities() {
        //List<Entity> entitiesCopy = new ArrayList<>(level.getEntities());
        for (int i = 0; i < level.getEntitiesCollection().getEntities().size(); i++) {
            if (level.getEntitiesCollection().getEntities().size() > i) {
                level.getEntitiesCollection().getEntities().get(i).update();
            }
        }
    }
    /**
     * Update game.
     *
     * @param deltaTime the delta time
     * @throws IOException the io exception
     */
    private void updateGame(double deltaTime) throws IOException {

        if (keys[KeyCode.LEFT.getCode()]) {
            moveLeftCommand.execute();
        }
        if (keys[KeyCode.RIGHT.getCode()]) {
            moveRightCommand.execute();
        }
        if (keys[KeyCode.UP.getCode()]) {
            jumpCommand.execute();
        }
        if (keys[KeyCode.SPACE.getCode()]) {
            fireCommand.execute();
        }

        updateEntities();
    }

    /**
     * Stop game loop.
     */
    public void stopGameLoop() {
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * Restart game loop.
     */
    public void restartGameLoop() {
        if (timer != null) {
            timer.start();
        }
    }
    private void resetKeyStates() {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
    }
    public void resetController() {
        resetKeyStates();
        System.gc();
    }


    /**
     * Gets score label.
     *
     * @return the score label
     */
    public Node getScoreLabel() {
        return levelView.getScoreLabel();
    }
}