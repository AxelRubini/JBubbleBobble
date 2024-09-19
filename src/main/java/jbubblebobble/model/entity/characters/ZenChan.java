package jbubblebobble.model.entity.characters;

import jbubblebobble.model.entity.PlatformDetection;
import jbubblebobble.model.level.Level;

/**
 * Zen chan enemy class .
 */
public class ZenChan extends Enemy implements PlatformDetection,Walker {


    /**
     * Instantiates a new Zen chan.
     *
     * @param x     the x
     * @param y     the y
     * @param level the level
     */
    public ZenChan(double x, double y, Level level) {
        super(x, y,level);
        init();
    }

    private void init(){
        facingRight = true;
        state = EnemyState.FACING_RIGHT;
        speed = 1;
    }

    @Override
    protected void moveAI() {

        walkerMove(this);

    }


    @Override
    protected void jump() {

        walkerJump(this);

    }



    @Override
    public String toString() {
        return "ZenChan";
    }

}
