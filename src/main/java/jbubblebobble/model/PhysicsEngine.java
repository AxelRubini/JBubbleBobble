package jbubblebobble.model;

import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.level.Level;
import jbubblebobble.model.level.Wall;
import utility.Config;

/**
 * there is a class PhysicsEngine that is responsible for the physics of the game.
 *
 */
public class PhysicsEngine {

    /**
     * Apply gravity.
     *
     * @param entity the entity
     */
    public static void applyGravity(Entity entity) {

        entity.setVelocityY(entity.getVelocityY() + Config.GRAVITY);
        if (entity.getVelocityY() > Config.MAX_FALL_SPEED) {
            entity.setVelocityY(Config.MAX_FALL_SPEED);
        }
        entity.setY(entity.getY() + entity.getVelocityY());
    }
}