package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
import utility.Config;

import java.util.Timer;
import java.util.TimerTask;

/**
 * PurpleGum power up strategy
 */
public class PurpleGumStrategy implements PowerUpStrategy {
    /**
     *make the bubble travel further.
     * @param level  the current level instance where the bubbles are
     */
    @Override
    public void applyPowerUp( Level level) {
        Player player = level.getPlayer();
        player.setPowerUpPurpleGum(true);
        player.setPurpleGumCount(player.getPurpleGumCount() + 1);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.setPowerUpPurpleGum(false);
            }
        }, Config.TIMED_POWER_UP);
    }
}
