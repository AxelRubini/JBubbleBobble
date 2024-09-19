package jbubblebobble.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utility.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class Bubble view is a container for the images of the bubbles.
 */
public  class BubbleView {

    private static Map<String, List<String>> bubbleImageMap;


    /**
     * Instantiates a new Bubble view.
     */
    public BubbleView() {
        initializeBubbleImageMap();
    }

    private void initializeBubbleImageMap() {
        bubbleImageMap = new HashMap<>();
        bubbleImageMap.put("CASTING", List.of(Config.PATH_TO_SPRITE + "bubble/bubble_1.png", Config.PATH_TO_SPRITE + "bubble/bubble_2.png", Config.PATH_TO_SPRITE + "bubble/bubble_3.png"));
        bubbleImageMap.put("MOVING", List.of(Config.PATH_TO_SPRITE + "bubble/bubble_4.png", Config.PATH_TO_SPRITE + "bubble/bubble_5.png", Config.PATH_TO_SPRITE + "bubble/bubble_6.png"));
        bubbleImageMap.put("POPPING", List.of(Config.PATH_TO_SPRITE + "bubble/bubble_6.png", Config.PATH_TO_SPRITE + "bubble/bubble_explosion.png"));
    }

    /**
     * Gets bubble image map.
     *
     * @return the bubble image map
     */
    public static Map<String, List<String>> getBubbleImageMap() {
        return bubbleImageMap;
    }

}
