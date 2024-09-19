package jbubblebobble.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utility.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class Zen chan view is a container for the images of the Zen chan enemy.
 */
public class ZenChanView {

    private static Map<String, List<String>> zenChanImageMap;

    /**
     * Instantiates a new Zen chan view.
     */
    public ZenChanView() {
        initializeImageMap();
    }

    private void initializeImageMap() {

        zenChanImageMap = new HashMap<>();
        zenChanImageMap.put("FACING_RIGHT", List.of(Config.PATH_TO_SPRITE + "Enemies/zen_chan_right_0.png", Config.PATH_TO_SPRITE + "Enemies/zen_chan_right_1.png"));
        zenChanImageMap.put("FACING_LEFT", List.of(Config.PATH_TO_SPRITE + "Enemies/zen_chan_left_0.png", Config.PATH_TO_SPRITE + "Enemies/zen_chan_left_1.png"));
        zenChanImageMap.put("VULNERABLE_RIGHT", List.of(Config.PATH_TO_SPRITE + "Enemies/zen_chan_right_vulnerable_0.png", Config.PATH_TO_SPRITE + "Enemies/zen_chan_right_vulnerable_1.png"));
        zenChanImageMap.put("VULNERABLE_LEFT", List.of(Config.PATH_TO_SPRITE + "Enemies/zen_chan_left_vulnerable_0.png", Config.PATH_TO_SPRITE + "Enemies/zen_chan_left_vulnerable_1.png"));
        zenChanImageMap.put("IS_IN_PRISON", List.of(Config.PATH_TO_SPRITE + "Enemies/zen_chan_bubble_prison_0.png", Config.PATH_TO_SPRITE + "Enemies/zen_chan_bubble_prison_1.png"));
        zenChanImageMap.put("DIE", List.of(Config.PATH_TO_SPRITE + "Enemies/zen_chan_die_0.png", Config.PATH_TO_SPRITE + "Enemies/zen_chan_die_1.png", Config.PATH_TO_SPRITE + "Enemies/zen_chan_die_2.png", Config.PATH_TO_SPRITE + "Enemies/zen_chan_die_3.png"));
    }

    /**
     * Gets zen chan image map.
     *
     * @return the zen chan image map
     */
    public static Map<String, List<String>> getZenChanImageMap() {
        return zenChanImageMap;
    }
}
