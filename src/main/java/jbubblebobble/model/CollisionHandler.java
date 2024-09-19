package jbubblebobble.model;

import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.level.Wall;

/**
 * Collision handler  contains static method to check if  one element is colliding with another..
 */
public class CollisionHandler {

    /**
     * check if two entities are colliding
     *
     * @param entity1 the entity 1
     * @param entity2 the entity 2
     * @return the boolean
     */
    public static boolean isColliding(Entity entity1, Entity entity2) {
        return entity1.getX() < entity2.getX() + entity2.getWidth() &&
               entity1.getX() + entity1.getWidth() > entity2.getX() &&
               entity1.getY() < entity2.getY() + entity2.getHeight() &&
               entity1.getY() + entity1.getHeight() > entity2.getY();
    }

    /**
     * check if entity is colliding with wall
     *
     * @param entity the entity
     * @param wall   the wall
     * @return the boolean
     */
    public static boolean isCollidingWithWall(Entity entity, Wall wall) {
        return entity.getX() < wall.getCoordinate().getX() + wall.getWidth() &&
               entity.getX() + entity.getWidth() > wall.getCoordinate().getX() &&
               entity.getY() < wall.getCoordinate().getY() + wall.getHeight() &&
               entity.getY() + entity.getHeight() > wall.getCoordinate().getY();
    }

    /**
     *  check if entity is colliding with top of the wall
     *
     * @param entity the entity
     * @param wall   the wall
     * @return the boolean
     */
    public static boolean isCollidingWithTopOfWall(Entity entity, Wall wall) {
        double enemyBottom = entity.getY() + entity.getHeight();
        double wallTop = wall.getCoordinate().getY();
        return enemyBottom > wallTop && entity.getY() < wallTop;
    }

    /**
     * check if entity is colliding with side of the wall
     *
     * @param entity the entity
     * @param wall   the wall
     * @return the boolean
     */
    public static boolean isCollidingWithSideOfWall(Entity entity, Wall wall) {
        double enemyRight = entity.getX() + entity.getWidth();
        double enemyLeft = entity.getX();
        double wallRight = wall.getCoordinate().getX() + wall.getWidth();
        double wallLeft = wall.getCoordinate().getX();
        return (enemyRight > wallLeft && enemyLeft < wallLeft) || (enemyLeft < wallRight && enemyRight > wallRight);
    }
}