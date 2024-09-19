package jbubblebobble.model.entity.characters;

import utility.Config;

/**
 * Define movement for walker type enemies.
 */
public interface Walker {

    /**
     * Walker move.
     *
     * @param enemy the enemy
     */
    default void walkerMove(Enemy enemy){
        if (enemy.getLevel().getPlayer().getState() == Player.PlayerState.DIE && !enemy.caughtByBubble){
            enemy.moveAwayFromPlayer();
            return;
        }
        if (!enemy.caughtByBubble) {
            if (enemy.getCoordinate().distance(enemy.getLevel().getPlayer().getCoordinate()) < 100 ) {
                enemy.moveTowardsPlayer();
            } else if (enemy.randomize()){
                enemy.setX(enemy.getX() + (enemy.isFacingRight() ? enemy.speed : -enemy.speed));
            }else{
                enemy.jump();
            }
        } else {
            enemy.setVelocityY(-0.5);
            enemy.setY(enemy.getY() + enemy.getVelocityY());
        }

    }

    /**
     * Walker jump.
     *
     * @param enemy the enemy
     */
    default void walkerJump(Enemy enemy){
        if (enemy.isOnPlatform(enemy.getLevel(),enemy)){
            enemy.setVelocityY(Config.JUMP_STRENGTH);
            if (enemy.randomize()){
                enemy.setVelocityX(enemy.randomize() ? 3 : -3);
            }
            enemy.setJumping(true);
        }
    }
}
