package jbubblebobble.view;

import utility.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GhostView implements Renderable {
    
    private static Map<String, List<String>> ghostImageMap;


    /**
     * Class GhostView is a container for the images of the ghost enemy.
     */
    public GhostView() {
        initializeImageMap();
    }

    private void initializeImageMap() {
        
        ghostImageMap = new HashMap<>();
        ghostImageMap.put("FACING_RIGHT", List.of(Config.PATH_TO_SPRITE+"Enemies/ghost_right_0.png", Config.PATH_TO_SPRITE+"Enemies/ghost_right_1.png"));
        ghostImageMap.put("FACING_LEFT", List.of(Config.PATH_TO_SPRITE+"Enemies/ghost_left_0.png", Config.PATH_TO_SPRITE+"Enemies/ghost_left_1.png"));
        ghostImageMap.put("VULNERABLE_RIGHT", List.of(Config.PATH_TO_SPRITE+"Enemies/ghost_vulnerable_right_0.png", Config.PATH_TO_SPRITE+"Enemies/ghost_vulnerable_right_1.png"));
        ghostImageMap.put("VULNERABLE_LEFT", List.of(Config.PATH_TO_SPRITE+"Enemies/ghost_vulnerable_left_0.png", Config.PATH_TO_SPRITE+"Enemies/ghost_vulnerable_left_1.png"));
        ghostImageMap.put("IS_IN_PRISON", List.of(Config.PATH_TO_SPRITE+"Enemies/ghost_bubble_prison_0.png", Config.PATH_TO_SPRITE+"Enemies/ghost_bubble_prison_1.png"));
        ghostImageMap.put("DIE", List.of(Config.PATH_TO_SPRITE+"Enemies/ghost_die_0.png", Config.PATH_TO_SPRITE+"Enemies/ghost_die_1.png", Config.PATH_TO_SPRITE+"Enemies/ghost_die_2.png", Config.PATH_TO_SPRITE+"Enemies/ghost_die_3.png"));
    }

    public static Map<String, List<String>> getGhostImageMap() {
        return ghostImageMap;
    }

    //    public static void render(GraphicsContext gc,List<List<String>> data){
//        for (List<String> ghostData : data){
//            List<String> frames = ghostImageMap.get(ghostData.get(2));
//            Image image = Flyweight.getImage(frames.get(Integer.parseInt(ghostData.get(3))));
//            gc.drawImage(image,Double.parseDouble(ghostData.get(0)), Double.parseDouble(ghostData.get(1)),20,20);
//        }
//
//    }
    
}
