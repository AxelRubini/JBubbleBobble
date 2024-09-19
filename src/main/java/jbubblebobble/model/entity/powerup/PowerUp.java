package jbubblebobble.model.entity.powerup;

import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.PlatformDetection;
import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.strategy.GlowingHeartStrategy;
import jbubblebobble.model.level.Level;
import jbubblebobble.model.level.Wall;
import utility.Config;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Power up.
 */
public  class PowerUp extends Entity implements PlatformDetection {
    private PowerUpStrategy powerUpStrategy;
    private PowerUpType powerUpType;
    private boolean active = true;
    private int frame = Config.STATIC_OBJECT_FRAME;

    /**
     * Instantiates a new Power up.
     *
     * @param x     the x
     * @param y     the y
     * @param level the level
     */
    public PowerUp(double x, double y, Level level) {
        super(x, y,level);
    }

    @Override
    protected  void handleEntityCollision(Entity entity) {

        if (entity instanceof Player && active){
            powerUpStrategy.applyPowerUp(level);
            frame = Config.STATIC_OBJECT_FRAME + 1;
            active = false;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    removePowerUp();
                }
            }, 1000);
        }

    }

    private void removePowerUp(){
        level.getEntitiesCollection().remove(this);
    }

    @Override
    protected void handleWallCollision(Wall wall) {

        if (isOnPlatform(level,this)){
            stopFall();
        }

    }

    @Override
    public List<String> getInfo() {
        return List.of(String.valueOf(getX()), String.valueOf(getY()), powerUpType.toString(), String.valueOf(frame));
    }

    @Override
    public void update() {

        checkEntityCollision();
        checkWallCollision();

    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets power up strategy.
     *
     * @param powerUpStrategy the power up strategy
     */
    public void setPowerUpStrategy(PowerUpStrategy powerUpStrategy) {
        this.powerUpStrategy = powerUpStrategy;
    }

    /**
     * Sets power up type.
     *
     * @param powerUpType the power up type
     */
    public void setPowerUpType(PowerUpType powerUpType) {
        this.powerUpType = powerUpType;
    }

    /**
     * The enum Power up type.
     */
    public enum PowerUpType {
        /**
         * Glowing heart power up type.
         */
        GLOWING_HEART,
        /**
         * Blue ring power up type.
         */
        BLUE_RING,
        /**
         * Purple ring power up type.
         */
        PURPLE_RING,
        /**
         * Red ring power up type.
         */
        RED_RING,
        /**
         * Extra life power up type.
         */
        EXTRA_LIFE,
        /**
         * Speed shoes power up type.
         */
        SPEED_SHOES,
        /**
         * Red cross power up type.
         */
        RED_CROSS,
        /**
         * Blue gum power up type.
         */
        BLUE_GUM,
        /**
         * Yellow gum power up type.
         */
        YELLOW_GUM,
        /**
         * Purple gum power up type.
         */
        PURPLE_GUM,
        /**
         * Apple power up type.
         */
        APPLE,
        /**
         * Cherry power up type.
         */
        CHERRY,
        /**
         * Grape power up type.
         */
        GRAPE;
    }
}
