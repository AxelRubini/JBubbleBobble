package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
/**
 * ExtraLife power up strategy
 */
public class ExtraLifeStrategy implements PowerUpStrategy {
    /**
     * Adds 1 life to the player's lives
     * @param level  the current level instance where the player is
     */
    @Override
    public void applyPowerUp( Level level) {
        Player player = level.getPlayer();
        player.setLives(player.getLives() + 1);
    }
}
