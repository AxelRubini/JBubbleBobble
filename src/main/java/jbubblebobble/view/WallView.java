package jbubblebobble.view;

import utility.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class WallView is a container for the images of the walls.
 */
public class WallView {
    private static Map<String, List<String>> wallImageMap;

    /**
     * Instantiates a new Wall view.
     */
    public WallView() {
        initializeImageMap();
    }

    private void initializeImageMap() {
        wallImageMap = new HashMap<>();
        wallImageMap.put("1", List.of(Config.PATH_TO_SPRITE + "Muri/wall_1.png"));
        wallImageMap.put("2", List.of(Config.PATH_TO_SPRITE + "Muri/wall_2.png"));
        wallImageMap.put("3", List.of(Config.PATH_TO_SPRITE + "Muri/wall_3.png"));
        wallImageMap.put("4", List.of(Config.PATH_TO_SPRITE + "Muri/wall_4.png"));
        wallImageMap.put("5", List.of(Config.PATH_TO_SPRITE + "Muri/wall_5.png"));
        wallImageMap.put("6", List.of(Config.PATH_TO_SPRITE + "Muri/wall_6.png"));
        wallImageMap.put("7", List.of(Config.PATH_TO_SPRITE + "Muri/wall_7.png"));
        wallImageMap.put("8", List.of(Config.PATH_TO_SPRITE + "Muri/wall_8.png"));
    }

    /**
     * Gets wall image map.
     *
     * @return the wall image map
     */
    public static Map<String, List<String>> getWallImageMap() {
        return wallImageMap;
    }
}
