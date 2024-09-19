package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
import utility.Config;

import java.util.Timer;

/**
 * YellowGum power up strategy
 */
public class YellowGumStrategy  implements PowerUpStrategy {
    /**
     * increase bubble firing speed.
     * @param level  the current level instance where the player is
     */
    @Override
    public void applyPowerUp( Level level) {
        Player player = level.getPlayer();
        player.setPowerUpFire(true);
        player.setYellowGumCount(player.getYellowGumCount() + 1);
        new Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                player.setPowerUpFire(false);
            }
        }, Config.TIMED_POWER_UP);
    }
}
