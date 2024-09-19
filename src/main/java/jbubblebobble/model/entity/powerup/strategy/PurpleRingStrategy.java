package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
import utility.Config;

import java.util.Timer;
import java.util.TimerTask;

/**
 * PurpleRing power up strategy
 */
public class PurpleRingStrategy implements PowerUpStrategy {
    /**
     *gives player 500 pts every time he jumps.
     * @param level  the current level instance where the player is
     */
    @Override
    public void applyPowerUp( Level level) {
        Player player = level.getPlayer();
        player.setPowerUpJumping(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.setPowerUpJumping(false);
            }
        }, Config.TIMED_POWER_UP);
    }
}
