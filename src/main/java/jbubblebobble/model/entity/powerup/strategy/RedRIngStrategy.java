package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
import utility.Config;

import java.util.Timer;
import java.util.TimerTask;

/**
 * RedRing power up strategy
 */
public class RedRIngStrategy  implements PowerUpStrategy {
    /**
     * gives player 100 pts for every bubble blown.
     * @param level  the current level instance where the player is
     */
    @Override
    public void applyPowerUp( Level level) {
        Player player = level.getPlayer();
           player.setPowerUpFire(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.setPowerUpFire(false);
            }
        }, Config.TIMED_POWER_UP);
    }
}
