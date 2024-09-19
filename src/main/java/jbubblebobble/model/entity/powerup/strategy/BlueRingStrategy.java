package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
import utility.Config;

import java.util.Timer;
import java.util.TimerTask;

/**
 * BlueRing power up strategy
 */
public class BlueRingStrategy implements PowerUpStrategy {
    /**
     * Player earn points if moving.
     * @param level  the current level instance where the player is
     */
    @Override
    public void applyPowerUp( Level level) {
        Player player = level.getPlayer();
        player.setPowerUpMoving(true);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.setPowerUpMoving(false);
            }
        }, Config.TIMED_POWER_UP);
    }
}
