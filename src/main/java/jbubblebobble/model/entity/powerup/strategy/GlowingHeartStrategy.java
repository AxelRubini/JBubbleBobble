package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Enemy;
import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
/**
 * GlowingHeart power up strategy
 */
public class GlowingHeartStrategy implements PowerUpStrategy {
    /**
     * All the enemies are vulnerable.
     * @param level  the current level instance where the enemies are
     */
    @Override
    public void applyPowerUp( Level level) {
        for (Enemy enemy : level.getEntitiesCollection().getEnemies()) {
            enemy.setVulnerable(true);
            enemy.setVulnerableTime(System.currentTimeMillis());
        }
    }
}
