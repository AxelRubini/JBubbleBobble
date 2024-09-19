package jbubblebobble.view;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import jbubblebobble.model.entity.characters.Player;
import utility.Config;

import java.util.*;

/**
 * Class PlayerView is a container for the images of the player.
 */
public class PlayerView  {

    private static Map<String, List<String>> playerImageMap;
    private int frameIndex;
    private long lastFrameTime;
    private LevelView levelView;

    /**
     * Instantiates a new Player view.
     */
    public PlayerView() {
        initializePlayerImageMap();
    }

    /**
     * Gets player image map.
     *
     * @return the player image map
     */
    public static Map<String, List<String>> getPlayerImageMap() {
        return playerImageMap;
    }

    private void initializePlayerImageMap() {
        playerImageMap = new HashMap<>();
        playerImageMap.put("IDLE_LEFT", List.of(Config.PATH_TO_SPRITE + "drake/drake_left_0.png", Config.PATH_TO_SPRITE + "drake/drake_left_1.png",Config.PATH_TO_SPRITE + "drake/drake_left_0.png", Config.PATH_TO_SPRITE + "drake/drake_left_1.png"));
        playerImageMap.put("IDLE_RIGHT", List.of(Config.PATH_TO_SPRITE + "drake/drake_right_0.png", Config.PATH_TO_SPRITE + "drake/drake_right_1.png",Config.PATH_TO_SPRITE + "drake/drake_right_0.png", Config.PATH_TO_SPRITE + "drake/drake_right_1.png"));
        playerImageMap.put("MOVING_LEFT", List.of(Config.PATH_TO_SPRITE + "drake/drake_left_2.png", Config.PATH_TO_SPRITE + "drake/drake_left_3.png",Config.PATH_TO_SPRITE + "drake/drake_left_2.png", Config.PATH_TO_SPRITE + "drake/drake_left_3.png"));
        playerImageMap.put("MOVING_RIGHT", List.of(Config.PATH_TO_SPRITE + "drake/drake_right_2.png", Config.PATH_TO_SPRITE + "drake/drake_right_3.png",Config.PATH_TO_SPRITE + "drake/drake_right_2.png", Config.PATH_TO_SPRITE + "drake/drake_right_3.png"));
        playerImageMap.put("JUMPING_LEFT", List.of(Config.PATH_TO_SPRITE + "drake/drake_left_5.png", Config.PATH_TO_SPRITE + "drake/drake_left_6.png",Config.PATH_TO_SPRITE + "drake/drake_left_5.png", Config.PATH_TO_SPRITE + "drake/drake_left_6.png"));
        playerImageMap.put("JUMPING_RIGHT", List.of(Config.PATH_TO_SPRITE + "drake/drake_right_5.png", Config.PATH_TO_SPRITE + "drake/drake_right_6.png",Config.PATH_TO_SPRITE + "drake/drake_right_5.png", Config.PATH_TO_SPRITE + "drake/drake_right_6.png"));
        playerImageMap.put("FIRING_LEFT", List.of(Config.PATH_TO_SPRITE + "drake/drake_left_Bubble.png", Config.PATH_TO_SPRITE + "drake/drake_left_Bubble.png", Config.PATH_TO_SPRITE + "drake/drake_left_Bubble.png", Config.PATH_TO_SPRITE + "drake/drake_left_Bubble.png"));
        playerImageMap.put("FIRING_RIGHT", List.of(Config.PATH_TO_SPRITE + "drake/drake_right_Bubble.png", Config.PATH_TO_SPRITE + "drake/drake_right_Bubble.png", Config.PATH_TO_SPRITE + "drake/drake_right_Bubble.png", Config.PATH_TO_SPRITE + "drake/drake_right_Bubble.png"));
        playerImageMap.put("DIE", List.of(Config.PATH_TO_SPRITE + "drake/drake_left_dead .png", Config.PATH_TO_SPRITE + "drake/drake_left_dead_1.png",Config.PATH_TO_SPRITE + "drake/drake_left_dead_2.png",Config.PATH_TO_SPRITE + "drake/drake_left_dead_3.png"));
    }

//    public static void render(GraphicsContext gc, List<String> data){
//        List<String> frames = playerImageMap.get(data.get(2));
//        Image image = Flyweight.getImage(frames.get(Integer.parseInt(data.get(3))));
//        gc.drawImage(image,Double.parseDouble(data.get(0))-4, Double.parseDouble(data.get(1))-4,24,24);
//    }

}
