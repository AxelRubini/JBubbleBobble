package jbubblebobble.model.entity.characters;

import jbubblebobble.model.entity.PlatformDetection;
import jbubblebobble.model.entity.bubble.EnemyBubble;
import jbubblebobble.model.level.Level;
import utility.Config;

public class Ghost extends Enemy implements PlatformDetection, Walker {
    private long lastFireTime;

    public Ghost(double x, double y, Level level) {
        super(x, y, level);
        init();
    }

    private void init() {
        facingRight = true;
        state = EnemyState.FACING_RIGHT;
        speed = 1;
    }

    public void fire() {
        if (System.currentTimeMillis() - lastFireTime > Config.EnemyFireRate && !caughtByBubble) {
            double bubbleX = (facingRight) ? getX() + getWidth() : getX();
            double bubbleY = getY();
            EnemyBubble bubble = new EnemyBubble(bubbleX, bubbleY, level, facingRight);
            level.getEntitiesCollection().add(bubble);
            lastFireTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void moveAI() {
        double playerY = level.getPlayer().getY();
        double ghostY = getY();
        if (playerY == ghostY) {
            fire();
        }
        walkerMove(this);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    protected void jump() {
        walkerJump(this);
    }

}
