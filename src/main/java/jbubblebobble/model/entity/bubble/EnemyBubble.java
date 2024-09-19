package jbubblebobble.model.entity.bubble;

import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.level.Level;
import jbubblebobble.model.level.Wall;
import utility.Config;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  Enemy bubble.
 */
public class EnemyBubble extends Entity {

    private EnemyBubbleState state;
    private boolean popped;


    /**
     * Instantiates a new Enemy bubble.
     *
     * @param x             the x
     * @param y             the y
     * @param level         the level
     * @param isFacingRight the is facing right
     */
    public EnemyBubble(double x, double y, Level level, boolean isFacingRight) {
        super(x, y, level);
        this.velocityX = ((isFacingRight) ? Config.BUBBLE_FLY_SPEED / 2 : -Config.BUBBLE_FLY_SPEED / 2);
        state = EnemyBubbleState.FLYING;
        frames = List.of(0, 1, 2, 3);
        addObserver(level);
    }


    /**
     * Sets popped.
     */
    public void setPopped() {
        state = EnemyBubbleState.POPPED;
        setVelocityX(0);
        currentFrame = 0;
        frames = List.of(0, 1);
    }

    /**
     * update the frame of the bubble using a different logic when the bubble is popped
     * */
    @Override
    protected void updateFrame() {
        if (state != EnemyBubbleState.POPPED) {
            super.updateFrame();
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= Config.FRAME_DURATION) {
            currentFrame++;
            lastFrameTime = currentTime;
            if (currentFrame >= frames.size() - 1) {
                currentFrame = frames.size() - 1;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        popped = true;
                    }
                }, 1000);
            }
        }


    }
    /**
     * the bubble can collide with the player and the kill the player
     * */
    @Override
    protected void handleEntityCollision(Entity entity) {
        if (entity instanceof Player player) {
            if (state == EnemyBubbleState.FLYING && player.getState() != Player.PlayerState.DIE) {
                player.loseLife();
                player.setState(Player.PlayerState.DIE);
                setPopped();
            }
        }

    }
    /**
     * the bubble can collide with the wall and pop
     * */
    @Override
    protected void handleWallCollision(Wall wall) {
        setPopped();
    }
    /**
     * Return the information of the bubble
     * @return List the information of the bubble
     * */
    @Override
    public List<String> getInfo() {
        return List.of(String.valueOf(getX()), String.valueOf(getY()), state.toString(), String.valueOf(currentFrame));
    }
    /**
     * Notify the changes of the bubble to the observers
     * and perform routine updates
     * */
    @Override
    public void update() {
        if (popped) {
            level.getEntitiesCollection().remove(this);
            return;
        }
        setX(getX() + velocityX);
        checkEntityCollision();
        checkWallCollision();
        updateFrame();
        setChanged();
        notifyObservers();

    }

    /**
     * The enum Enemy bubble state.
     */
    public enum EnemyBubbleState {
        /**
         * Flying enemy bubble state.
         */
        FLYING,
        /**
         * Popped enemy bubble state.
         */
        POPPED
    }
}
