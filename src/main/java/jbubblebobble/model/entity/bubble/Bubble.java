package jbubblebobble.model.entity.bubble;

import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.characters.Enemy;
import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.level.Level;
import jbubblebobble.model.level.Wall;
import utility.Config;

import java.util.List;

/**
 *  Bubble.
 */
public class Bubble extends Entity {
    private boolean floating;
    private long floatingDelay;
    private long creationTime;
    private final long liveTime = Config.BUBBLE_LIVE_TIME;
    private BubbleState state;
    private boolean popping;
    private double poppingTime;


    /**
     * Instantiates a new Bubble.
     *
     * @param x             the x
     * @param y             the y
     * @param isFacingRight the is facing right
     * @param level         the level
     * @param floatingDelay the floating delay
     */
    public Bubble(double x, double y, boolean isFacingRight, Level level, long floatingDelay) {
        super(x, y, level);

        this.velocityX = ((isFacingRight) ? Config.BUBBLE_FLY_SPEED : -Config.BUBBLE_FLY_SPEED) * (level.isBubbleSpeedPowerUp() ? 1.2 : 1);
        this.velocityY = 0;
        this.floating = false;
        this.creationTime = System.currentTimeMillis();
        this.state = BubbleState.CASTING;
        this.height = 10;
        this.floatingDelay = floatingDelay;
        addObserver(level);
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (popping) {
            poppingTime -= 0.016;
            if (poppingTime <= 0) {
                level.getEntitiesCollection().remove(this);
                setPopping(false);
            }
        }
        if (!floating && (currentTime - creationTime >= floatingDelay)) {
            setFloating();
        }
        if (floating) {
            setY(getY() - (level.isBubbleSpeedPowerUp() ? velocityY * 2 : velocityY)); // Float upwards
        } else {
            setX(getX() + (level.isBubbleSpeedPowerUp() ? velocityX * 2 : velocityX));
            setY(getY() + (level.isBubbleSpeedPowerUp() ? velocityY * 2 : velocityY));
        }
        checkEntityCollision();
        checkWallCollision();
        updateFrame();
        setChanged();
        notifyObservers();
    }

    @Override
    protected void handleEntityCollision(Entity entity) {
        if (entity instanceof Player player) {
            if (player.isJumping() && System.currentTimeMillis() - creationTime > liveTime / 2) {
                setPopping(true);
                List<Bubble> nearBubbles = level.getEntitiesCollection().getNearBubbles(this);
                for (Bubble bubble : nearBubbles) {
                    bubble.setPopping(true);
                    level.getPlayer().setBubbleExploded(level.getPlayer().getBubbleExploded() + 1);
                }
                velocityY = 0;
                velocityX = 0;
            } else if (player.getMoving() && !player.isJumping()) {
                setX(getX() + (player.isFacingRight() ? player.getSpeed() : -player.getSpeed()));

            } else {
                velocityX = -velocityX * 0.75;
            }
        } else if (entity instanceof Enemy enemy) {
            if (state == BubbleState.CASTING) {

                enemy.setCaughtByBubble(true);
                level.getEntitiesCollection().remove(this);
            }

        }
    }

    @Override
    protected void handleWallCollision(Wall wall) {
        if (!wall.isPlatform()) {
            if (state == BubbleState.CASTING) {
                setPopping(true);
                velocityX = 0;
            } else {
                if (velocityX != 0) {
                    velocityX = -velocityX * 0.5;
                    setY(getY() + velocityY * 0.5);
                } else {
                    setY(getY() + velocityY * 0.5);
                }
            }
        }
    }

    @Override
    protected void updateFrame() {
        long currentTime = System.currentTimeMillis();

        switch (state) {
            case CASTING:
                if (currentTime - lastFrameTime >= Config.FRAME_DURATION / 2 && currentFrame < 2) {
                    currentFrame++;
                    lastFrameTime = currentTime;
                }
                break;

            case MOVING:
                if (liveTime - (currentTime - creationTime) <= poppingTime) {
                    state = BubbleState.POPPING;
                    setPopping(true);
                } else if (liveTime - (currentTime - creationTime) <= Config.BUBBLE_LIVE_TIME / 3) {
                    if (currentTime - lastFrameTime >= Config.FRAME_DURATION) {
                        currentFrame = (currentFrame + 1) % frames.size();
                        lastFrameTime = currentTime;
                    }
                } else {
                    currentFrame = 0;
                }
                break;

            case POPPING:
                if (currentTime - lastFrameTime >= Config.FRAME_DURATION / 4) {
                    currentFrame++;
                    lastFrameTime = currentTime;
                    if (currentFrame >= frames.size()) {
                        currentFrame = frames.size() - 1;
                    }
                }
                break;
        }
    }

    @Override
    public List<String> getInfo() {
        return List.of(String.valueOf(getX()), String.valueOf(getY()), state.toString(), String.valueOf(currentFrame));
    }

    private void setFloating() {
        floating = true;
        velocityX = 0;
        velocityY = 0.2;
        setFrames(List.of(0, 1));
        state = BubbleState.MOVING;
    }

    /**
     * Sets popping.
     *
     * @param popping the popping
     */
    public void setPopping(boolean popping) {
        this.popping = popping;
        state = BubbleState.POPPING;
        setFrames(List.of(0, 1));
        currentFrame = 1;
        this.poppingTime = popping ? 0.2 : 0;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public BubbleState getState() {
        return state;
    }

    /**
     * Is floating boolean.
     *
     * @return the boolean
     */
    public boolean isFloating() {
        return floating;
    }

    @Override
    public String toString() {
        return "Bubble";
    }

    /**
     * The enum Bubble state.
     */
    public enum BubbleState {
        /**
         * Casting bubble state.
         */
        CASTING,
        /**
         * Moving bubble state.
         */
        MOVING,
        /**
         * Popping bubble state.
         */
        POPPING,
        /**
         * Popped bubble state.
         */
        POPPED


    }


}