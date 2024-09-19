package jbubblebobble.model.entity;

import jbubblebobble.model.level.Level;

/**
 *  Interface Platform detection.
 */
public interface PlatformDetection {

    /**
     * Check if the entity is on top of a wall .
     *
     * @param level  the level
     * @param entity the entity
     * @return the boolean
     */
    default boolean isOnPlatform(Level level, Entity entity) {

        return level.getWallsCollection().getNearWalls(entity).stream()
                .anyMatch(wall ->entity.getY() + entity.getHeight() == wall.getCoordinate().getY() && entity.getX() + entity.getWidth() > wall.getCoordinate().getX() && entity.getX() < wall.getCoordinate().getX() + wall.getWidth());

    }
}
