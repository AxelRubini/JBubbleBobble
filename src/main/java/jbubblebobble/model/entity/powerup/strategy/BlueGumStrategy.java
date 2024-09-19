package jbubblebobble.model.entity.powerup.strategy;

import jbubblebobble.model.entity.bubble.Bubble;
import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUpStrategy;
import jbubblebobble.model.level.Level;
import utility.Config;

import java.util.Timer;

/**
 * BlueGum power up strategy
 */
public class BlueGumStrategy implements PowerUpStrategy {
    /**
     * increase the bubble speed across screen.
     * @param level  the current level instance where the bubbles are
     */
    @Override
    public void applyPowerUp( Level level) {
        level.setBubbleSpeedPowerUp(true);
        level.getPlayer().setBlueGumCount(level.getPlayer().getBlueGumCount() + 1);
        for (Bubble bubble : level.getEntitiesCollection().getBubbles()) {
            bubble.setVelocityX(bubble.getVelocityX() * 2);
            bubble.setVelocityY(bubble.getVelocityY() * 2);
        }
        new Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                for (Bubble bubble : level.getEntitiesCollection().getBubbles()) {
                    bubble.setVelocityX(bubble.getVelocityX() / 2);
                    bubble.setVelocityY(bubble.getVelocityY() / 2);
                }
                level.setBubbleSpeedPowerUp(false);
            }
        }, Config.TIMED_POWER_UP);
    }
}
