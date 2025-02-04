package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;

/**
 * Cherry power up strategy
 */
public class CherryStrategy implements PowerUpStrategy {
    /**
     * Adds 200 points to the player's score
     * @param level  the current level instance where the player is
     */
    @Override
    public void applyPowerUp( Level level) {
        Player player = level.getPlayer();
        player.setScore(player.getScore() + 1000);
    }
}
