package jbubblebobble.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utility.Config;

import java.util.List;
import java.util.Map;

/**
 * The interface Renderable implements the render methods for the entities, power ups and walls.
 */
public interface Renderable {
    /**
     * Entity render.
     *
     * @param gc       the gc
     * @param imageMap the image map
     * @param data     the data
     */
    default void entityRender(GraphicsContext gc , Map<String,List<String>> imageMap, List<List<String>> data){
        for (List<String> entityData : data){
            List<String> frames = imageMap.get(entityData.get(2));
            Image image = Flyweight.getImage(frames.get(Integer.parseInt(entityData.get(3))));
            gc.drawImage(image,Double.parseDouble(entityData.get(0))-4, Double.parseDouble(entityData.get(1))-4,24,24);
        }
    }

    /**
     * Power up render.
     *
     * @param gc       the gc
     * @param imageMap the image map
     * @param data     the data
     */
    default void powerUpRender(GraphicsContext gc , Map<String,List<String>> imageMap, List<List<String>> data){
        for (List<String> powerUpData : data){
            List<String> frames = imageMap.get(powerUpData.get(2));
            Image image = Flyweight.getImage(frames.get(Integer.parseInt(powerUpData.get(3))));
            gc.drawImage(image,Double.parseDouble(powerUpData.get(0)), Double.parseDouble(powerUpData.get(1)), Config.TILE_SIZE,Config.TILE_SIZE);
        }
    }

    /**
     * Wall render.
     *
     * @param gc   the gc
     * @param data the data
     */
    default void wallRender(GraphicsContext gc, List<List<String>> data){
        for (List<String> wallData : data){
            List<String> frames = WallView.getWallImageMap().get(wallData.get(2));
            Image image = Flyweight.getImage(frames.get(0));
            gc.drawImage(image,Double.parseDouble(wallData.get(0)), Double.parseDouble(wallData.get(1)), Config.TILE_SIZE,Config.TILE_SIZE);
        }
    }
}
