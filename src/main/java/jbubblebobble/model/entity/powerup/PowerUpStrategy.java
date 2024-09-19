package jbubblebobble.model.entity.powerup;


import jbubblebobble.model.level.Level;

/**
 * Power up strategy.
 */
public interface PowerUpStrategy {

    /**
     * Apply power up.
     *
     * @param level the level
     */
    void applyPowerUp(Level level);
}
