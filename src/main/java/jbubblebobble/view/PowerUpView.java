package jbubblebobble.view;

import utility.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class PowerUpView is a container for the images of the power ups.
 */
public class PowerUpView {
    private static Map<String, List<String>> powerUpImageMap;

    /**
     * Instantiates a new Power up view.
     */
    public PowerUpView() {
        initializeImageMap();
    }

    private void initializeImageMap() {
        powerUpImageMap = new HashMap<>();
        powerUpImageMap.put("APPLE", List.of(Config.PATH_TO_SPRITE + "PowerUps/apple.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("CHERRY", List.of(Config.PATH_TO_SPRITE + "PowerUps/cherry.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("PURPLE_GUM", List.of(Config.PATH_TO_SPRITE + "PowerUps/purple_gum.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("YELLOW_GUM", List.of(Config.PATH_TO_SPRITE + "PowerUps/yellow_gum.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("BLUE_GUM", List.of(Config.PATH_TO_SPRITE + "PowerUps/blue_gum.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("RED_CROSS", List.of(Config.PATH_TO_SPRITE + "PowerUps/bible.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("SPEED_SHOES", List.of(Config.PATH_TO_SPRITE + "PowerUps/red_shoe.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("EXTRA_LIFE", List.of(Config.PATH_TO_SPRITE + "PowerUps/hearth.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("RED_RING", List.of(Config.PATH_TO_SPRITE + "PowerUps/red_ring.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("PURPLE_RING", List.of(Config.PATH_TO_SPRITE + "PowerUps/purple_ring.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("BLUE_RING", List.of(Config.PATH_TO_SPRITE + "PowerUps/blue_ring.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("GLOWING_HEART", List.of(Config.PATH_TO_SPRITE + "PowerUps/glowing_heart.png",  Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
        powerUpImageMap.put("GRAPE", List.of(Config.PATH_TO_SPRITE + "PowerUps/grape.png", Config.PATH_TO_SPRITE + "punti1/green_500.gif"));
    }

    /**
     * Gets power up image map.
     *
     * @return the power up image map
     */
    public static Map<String, List<String>> getPowerUpImageMap() {
        return powerUpImageMap;
    }
}
