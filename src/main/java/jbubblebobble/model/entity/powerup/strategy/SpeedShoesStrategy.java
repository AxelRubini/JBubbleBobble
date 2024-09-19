package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
import utility.Config;

import java.util.Timer;
import java.util.TimerTask;

/**
 * SpeedShoes power up strategy
 */
public class SpeedShoesStrategy implements PowerUpStrategy {
    /**
     * Increase player's speed by 1
     * @param level  the current level instance where the player is
     */
    @Override
    public void applyPowerUp( Level level) {
        Player player = level.getPlayer();
        player.setSpeed(player.getSpeed()*2);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.setSpeed(player.getSpeed()/2);
            }
        }, Config.TIMED_POWER_UP);

    }
}
