package jbubblebobble.model.entity.powerup;

import jbubblebobble.model.entity.powerup.strategy.*;
import jbubblebobble.model.level.Level;

/**
 * Power up factory.
 */
public class PowerUpFactory {

    /**
     * Create power up.
     *
     * @param x     the x
     * @param y     the y
     * @param level the level
     * @param type  the type
     * @return the power up
     */
    public static PowerUp createPowerUp(double x, double y, Level level,PowerUp.PowerUpType type){
        PowerUp powerUp = new PowerUp(x, y, level);
        switch (type){
            case GLOWING_HEART:
                powerUp.setPowerUpType(PowerUp.PowerUpType.GLOWING_HEART);
                powerUp.setPowerUpStrategy(new GlowingHeartStrategy());
                break;
            case BLUE_RING:
                powerUp.setPowerUpType(PowerUp.PowerUpType.BLUE_RING);
                powerUp.setPowerUpStrategy(new BlueRingStrategy());
                break;
            case PURPLE_RING:
                powerUp.setPowerUpType(PowerUp.PowerUpType.PURPLE_RING);
                powerUp.setPowerUpStrategy(new PurpleRingStrategy());
                break;
            case RED_RING:
                powerUp.setPowerUpType(PowerUp.PowerUpType.RED_RING);
                powerUp.setPowerUpStrategy(new RedRIngStrategy());
                break;
            case EXTRA_LIFE:
                powerUp.setPowerUpType(PowerUp.PowerUpType.EXTRA_LIFE);
                powerUp.setPowerUpStrategy(new ExtraLifeStrategy());
                break;
            case SPEED_SHOES:
                powerUp.setPowerUpType(PowerUp.PowerUpType.SPEED_SHOES);
                powerUp.setPowerUpStrategy(new SpeedShoesStrategy());
                break;
            case RED_CROSS:
                powerUp.setPowerUpType(PowerUp.PowerUpType.RED_CROSS);
                powerUp.setPowerUpStrategy(new RedCrossStrategy());
                break;
            case BLUE_GUM:
                powerUp.setPowerUpType(PowerUp.PowerUpType.BLUE_GUM);
                powerUp.setPowerUpStrategy(new BlueGumStrategy());
                break;
            case YELLOW_GUM:
                powerUp.setPowerUpType(PowerUp.PowerUpType.YELLOW_GUM);
                powerUp.setPowerUpStrategy(new YellowGumStrategy());
                break;
            case PURPLE_GUM:
                powerUp.setPowerUpType(PowerUp.PowerUpType.PURPLE_GUM);
                powerUp.setPowerUpStrategy(new PurpleGumStrategy());
                break;
            case APPLE:
                powerUp.setPowerUpType(PowerUp.PowerUpType.APPLE);
                powerUp.setPowerUpStrategy(new AppleStrategy());
                break;
            case CHERRY:
                powerUp.setPowerUpType(PowerUp.PowerUpType.CHERRY);
                powerUp.setPowerUpStrategy(new CherryStrategy());
                break;
            case GRAPE:
                powerUp.setPowerUpType(PowerUp.PowerUpType.GRAPE);
                powerUp.setPowerUpStrategy(new GrapeStrategy());
                break;
        }
        return powerUp;
    }
}
