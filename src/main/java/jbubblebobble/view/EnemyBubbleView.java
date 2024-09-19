package jbubblebobble.view;

import jbubblebobble.model.entity.characters.Enemy;
import utility.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class Enemy bubble view is a container for the images of the enemy bubble.
 */
public class EnemyBubbleView {
    private static Map<String, List<String>> enemyBubbleImageMap;

    /**
     * Instantiates a new Enemy bubble view.
     */
    public EnemyBubbleView() {
        initializeImageMap();
    }
    private void initializeImageMap() {
        enemyBubbleImageMap = new HashMap<>();
        enemyBubbleImageMap.put("FLYING", List.of(Config.PATH_TO_SPRITE + "Enemies/ghost_spell_1.png", Config.PATH_TO_SPRITE + "Enemies/ghost_spell_2.png", Config.PATH_TO_SPRITE + "Enemies/ghost_spell_3.png", Config.PATH_TO_SPRITE + "Enemies/ghost_spell_4.png"));
        enemyBubbleImageMap.put("POPPED", List.of(Config.PATH_TO_SPRITE + "Enemies/ghost_spell_5.png", Config.PATH_TO_SPRITE + "Enemies/ghost_spell_6.png"));
    }

    /**
     * Gets enemy bubble image map.
     *
     * @return the enemy bubble image map
     */
    public static Map<String, List<String>> getEnemyBubbleImageMap() {
        return enemyBubbleImageMap;
    }
}
