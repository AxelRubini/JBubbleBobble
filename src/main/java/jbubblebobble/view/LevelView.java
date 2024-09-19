package jbubblebobble.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jbubblebobble.model.level.Level;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import utility.Config;

import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Observable;


/**
 * the class LevelView Draws the level on the canvas
 * and updates the level when the observable object is changed
 */
public class LevelView extends Canvas implements Observer, Renderable {

    private int score ;
    private  Label scoreLabel;
    private Font customFont;
    private Label lifesLabel;


    /**
     * Instantiates a new Level view
     * by setting the width and height of the canvas
     * and creating the player, bubble, zenchan, ghost, monster, powerup, enemybubble and wall views
     *
     */
    public LevelView() {
        setLayoutY(40);
        setWidth(Config.SCREEN_WIDTH); // Set appropriate width
        setHeight(Config.SCREEN_HEIGHT); // Set appropriate height
        setFocusTraversable(true);
        requestFocus();
        new PlayerView();
        new BubbleView();
        new ZenChanView();
        new GhostView();
        new MonsterView();
        new PowerUpView();
        new EnemyBubbleView();
        new WallView();
        score = 0;
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/sprite/Font-font.ttf"), 20);
        this.scoreLabel=new Label();
        this.lifesLabel=new Label();
        scoreLabel.setFont(customFont);
        scoreLabel.setTextFill(Color.WHITE);
        lifesLabel.setFont(customFont);
        lifesLabel.setTextFill(Color.WHITE);
    }

    /**
     * draw the images on a canvas
     *
     * @param levelMap the level map
     */
    public void render(Map<String, List<List<String>>> levelMap) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());

        List<List<String>> wallData = levelMap.get("Wall");
        wallRender(gc,wallData);

        for (String type : levelMap.keySet()) {
            List<List<String>> data = levelMap.get(type);
            switch (type) {
                case "ZenChan":
                    entityRender(gc,ZenChanView.getZenChanImageMap(),data);
                    break;
                case "Ghost":
                    entityRender(gc,GhostView.getGhostImageMap(),data);
                    break;
                case "Monster":
                    entityRender(gc,MonsterView.getMonsterImageMap(),data);
                    break;
                case "Player":
                    entityRender(gc,PlayerView.getPlayerImageMap(),data);
                    break;
                case "Bubble":
                    entityRender(gc,BubbleView.getBubbleImageMap(),data);
                    break;
                case "PowerUp":
                    powerUpRender(gc,PowerUpView.getPowerUpImageMap(),data);
                    break;
                case "EnemyBubble":
                    entityRender(gc,EnemyBubbleView.getEnemyBubbleImageMap(),data);
                    break;
            }
        }

    }

    /**
     * Update called when the observable object is changed
     * the observable object is the level
     * the arg is the level map
     * from the level map we get the data to render the level
     *
     * @param o   the o
     * @param arg the arg
     */
    public void update(Observable o, Object arg) {
       Map<String,List<List<String>>> levelMap = (Map<String, List<List<String>>>) arg;
       scoreLabel.setText("score: " + levelMap.get("Player").get(0).get(5));
       lifesLabel.setText("lifes: " + levelMap.get("Player").get(0).get(4));
        render(levelMap);
    }

    /**
     * Gets lifes label.
     *
     * @return the lifes label
     */
    public Node getLifesLabel() {
        return lifesLabel;
    }

    /**
     * Gets score label.
     *
     * @return the score label
     */
    public Node getScoreLabel() {
        return scoreLabel;
    }
}