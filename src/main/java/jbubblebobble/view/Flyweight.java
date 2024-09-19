package jbubblebobble.view;

import javafx.scene.image.Image;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight class that creates and stores images in a map to avoid creating the same image multiple times.
 */
public class Flyweight {
    private static final Map<String, Image> imageMap = new HashMap<>();

    /**
     * returns the image from the image path if it exists in the map, otherwise it creates a new image and adds it to the map.
     *
     * @param imagePath the image path
     * @return the image
     */
    public static Image getImage(String imagePath) {
        return imageMap.computeIfAbsent(imagePath,im-> {
            try {
                return new Image(Flyweight.class.getResource(imagePath).toURI().toString());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }
}