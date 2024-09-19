package jbubblebobble.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utility.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class MonsterView is a container for the images of the monster enemy.
 */
public class MonsterView {
    private static Map<String, List<String>> monsterImageMap;


    /**
     * Instantiates a new Monster view.
     */
    public MonsterView() {
        initializeImageMap();
    }

    private void initializeImageMap() {

        monsterImageMap = new HashMap<>();
        monsterImageMap.put("FACING_RIGHT", List.of("/sprite/Enemies/monster_right_0.png", Config.PATH_TO_SPRITE+"Enemies/monster_right_1.png"));
        monsterImageMap.put("FACING_LEFT", List.of(Config.PATH_TO_SPRITE+"Enemies/monster_left_0.png", Config.PATH_TO_SPRITE+"Enemies/monster_left_1.png"));
        monsterImageMap.put("VULNERABLE_RIGHT", List.of(Config.PATH_TO_SPRITE+"Enemies/monster_vulnerable_right_0.png", Config.PATH_TO_SPRITE+"Enemies/monster_vulnerable_right_1.png"));
        monsterImageMap.put("VULNERABLE_LEFT", List.of(Config.PATH_TO_SPRITE+"Enemies/monster_vulnerable_left_0.png", Config.PATH_TO_SPRITE+"Enemies/monster_vulnerable_left_1.png"));
        monsterImageMap.put("IS_IN_PRISON", List.of(Config.PATH_TO_SPRITE+"Enemies/monster_bubble_prison_0.png", Config.PATH_TO_SPRITE+"Enemies/monster_bubble_prison_1.png"));
        monsterImageMap.put("DIE", List.of(Config.PATH_TO_SPRITE+"Enemies/monster_die_0.png", Config.PATH_TO_SPRITE+"Enemies/monster_die_1.png", Config.PATH_TO_SPRITE+"Enemies/monster_die_2.png", Config.PATH_TO_SPRITE+"Enemies/monster_die_3.png"));
    }

    /**
     * Gets monster image map.
     *
     * @return the monster image map
     */
    public static Map<String, List<String>> getMonsterImageMap() {
        return monsterImageMap;
    }
//    public static void render(GraphicsContext gc, List<List<String>> data){
//        for (List<String> ghostData : data){
//            List<String> frames = monsterImageMap.get(ghostData.get(2));
//            Image image = Flyweight.getImage(frames.get(Integer.parseInt(ghostData.get(3))));
//            gc.drawImage(image,Double.parseDouble(ghostData.get(0)), Double.parseDouble(ghostData.get(1)),20,20);
//        }
//
//    }
}
