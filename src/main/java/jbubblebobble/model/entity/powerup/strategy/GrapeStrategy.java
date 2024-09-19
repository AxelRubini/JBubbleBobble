package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;

/**
 *  Grape strategy.
 */
public class GrapeStrategy implements PowerUpStrategy {

    /**
     * Adds 5000 points to the player's score
     * @param level  the current level instance where the player is
     */
    @Override
    public void applyPowerUp(Level level) {
        Player player = level.getPlayer();
        player.setScore(player.getScore() + 5000);
    }
}
