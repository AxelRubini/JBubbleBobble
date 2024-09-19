package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Enemy;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
/**
 * RedCross power up strategy
 */
public class RedCrossStrategy implements PowerUpStrategy {
    /**
     *make all the enemies die.
     * @param level  the current level instance where the enemies are
     */
    @Override
    public void applyPowerUp( Level level) {
        for (Enemy enemy : level.getEntitiesCollection().getEnemies()) {
            enemy.setDie(true);
        }
    }
}
