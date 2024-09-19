package jbubblebobble.model.entity.characters;

import jbubblebobble.model.CollisionHandler;
import jbubblebobble.model.PhysicsEngine;
import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.PlatformDetection;
import jbubblebobble.model.entity.powerup.PowerUp;
import jbubblebobble.model.entity.powerup.PowerUpFactory;
import jbubblebobble.model.level.Level;
import jbubblebobble.model.level.Wall;
import utility.Config;

import java.util.List;

/**
 * The type Enemy.
 */
public abstract class Enemy extends Entity implements PlatformDetection {
    /**
     * The Caught by bubble.
     */
    protected boolean caughtByBubble;
    /**
     * The Speed.
     */
    protected double speed;
    /**
     * The Vulnerable.
     */
    protected boolean vulnerable;
    /**
     * The Jumping.
     */
    protected boolean jumping;
    /**
     * The Able to fly.
     */
    protected boolean ableToFly;
    /**
     * The State.
     */
    protected EnemyState state;
    /**
     * The Die.
     */
    protected boolean die;
    /**
     * The Die time.
     */
    protected long dieTime;
    /**
     * The Vulnerable time.
     */
    protected long vulnerableTime;


    /**
     * Instantiates a new Enemy.
     *
     * @param x     the x
     * @param y     the y
     * @param level the level
     */
    public Enemy(double x, double y, Level level) {
        super(x, y, level);
        this.frames = List.of(0, 1);
    }

    /**
     * Movement behavior of the enemy.
     */
    protected abstract void moveAI();

    protected void handleEntityCollision(Entity entity) {

    }

    protected void handleWallCollision(Wall wall) {
        double enemyLeft = getX();
        double enemyRight = getX() + getWidth();
        double enemyTop = getY();
        double enemyBottom = getY() + getHeight();

        double wallLeft = wall.getCoordinate().getX();
        double wallRight = wall.getCoordinate().getX() + wall.getWidth();
        double wallTop = wall.getCoordinate().getY();
        double wallBottom = wall.getCoordinate().getY() + wall.getHeight();

        if (!wall.isPlatform()) {
            if (caughtByBubble) {
                if (enemyBottom > wallTop && enemyTop < wallTop) {
                    // Collision from the top
                    setY(wallTop - getHeight());
                } else if (enemyTop < wallBottom && enemyBottom > wallBottom) {
                    // Collision from the top
                    setY(wallBottom);
                }
                return;
            }
            if (enemyBottom > wallTop && enemyTop < wallTop) {
                // Collision from the top
                setY(wallTop - getHeight());

                jumping = false; // Stop jumping if colliding from the top
                velocityX = 0;
                velocityY = 0;
                // Determine the type of collision
            } else if (enemyRight > wallLeft && enemyLeft < wallLeft) {
                // Collision from the left
                setX(wallLeft - getWidth());
                if (caughtByBubble) {
                    return;
                }
                facingRight = !facingRight;
                state = EnemyState.FACING_LEFT;
            } else if (enemyLeft < wallRight && enemyRight > wallRight) {
                // Collision from the right
                setX(wallRight);
                if (caughtByBubble) {
                    return;
                }
                facingRight = !facingRight;
                state = EnemyState.FACING_RIGHT;
            } else if (enemyTop < wallBottom && enemyBottom > wallBottom) {
                // Collision from the bottom
                setY(wallBottom);
            }
        } else {
            if (enemyBottom > wallTop && enemyTop < wallTop && velocityY > 0) {
                // Collision from the top
                setY(wallTop - getHeight());

                jumping = false; // Stop jumping if colliding from the top
                velocityX = 0;
                velocityY = 0;
            }
        }

    }

    /**
     * Move towards player.
     */
    protected void moveTowardsPlayer() {
        if (caughtByBubble) {
            return;
        }
        if (level.getPlayer().getX() < getX()) {
            facingRight = false;
            state = EnemyState.FACING_LEFT;
            setX(getX() - speed);
        } else {
            facingRight = true;
            state = EnemyState.FACING_RIGHT;
            setY(getY() + speed);
        }
        if (level.getPlayer().getY() < getY()) {
            if (ableToFly) {
                setY(getY() - speed);
            } else {
                jump();
            }

        } else {
            setY(getY() + speed);
        }
    }

    /**
     * Move away from player.
     */
    protected void moveAwayFromPlayer() {
        if (caughtByBubble) {
            return;
        }
        if (level.getPlayer().getX() <= getX()) {
            facingRight = true;
            state = EnemyState.FACING_RIGHT;
            setX(getX() + (speed * 0.5));
        } else {
            facingRight = false;
            state = EnemyState.FACING_LEFT;
            setX(getX() - (speed * 0.5));
        }
        if (level.getPlayer().getY() <= getY() && ableToFly) {
            setY(getY() + (speed * 0.5));
        } else {
            setY(getY() - (speed * 0.5));
        }
    }

    /**
     * Sets jumping.
     *
     * @param jumping the jumping
     */
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void update() {
        if (die) {
            dieRoutine();
        } else if (vulnerable) {
            vulnerableRoutine();
        } else {
            moveAI();
            if (!isOnPlatform(level, this)) {
                setVelocityY(getVelocityY() + Config.GRAVITY);
                move(0, getVelocityY());
                handlePacManEffect();
            }
            checkWallCollision();
        }
        updateFrame();
        setChanged();
        notifyObservers();
    }

    /**
     * Jump.
     */
    protected void jump() {
    }

    ;

    /**
     * Is caught by bubble boolean.
     *
     * @return the boolean
     */
    public boolean isCaughtByBubble() {
        return caughtByBubble;
    }

    /**
     * Sets caught by bubble.
     *
     * @param isCaughtByBubble the is caught by bubble
     */
    public void setCaughtByBubble(boolean isCaughtByBubble) {
        this.caughtByBubble = isCaughtByBubble;
        state = EnemyState.IS_IN_PRISON;


    }

    /**
     * Sets die.
     *
     * @param die the die
     */
    public void setDie(boolean die) {
        this.die = die;
        state = EnemyState.DIE;
        setVelocityY(Config.JUMP_STRENGTH);
        setVelocityX(randomize() ? 1.5 : -1.5);
        dieTime = System.currentTimeMillis();
        this.frames = List.of(0, 1, 2, 3);
        update();
    }

    /**
     * Sets is facing right.
     *
     * @param isFacingRight the is facing right
     */
    public void setIsFacingRight(boolean isFacingRight) {
        this.facingRight = isFacingRight;
    }

    /**
     * Is vulnerable boolean.
     *
     * @return the boolean
     */
    public boolean isVulnerable() {
        return vulnerable;
    }

    /**
     * Randomize boolean.
     *
     * @return the boolean
     */
    protected boolean randomize() {

        return Math.random() * 10 < 5.0;

    }

    public boolean isDie() {
        return die;
    }

    public List<String> getInfo() {
        return List.of(String.valueOf(getX()), String.valueOf(getY()), state.toString(), String.valueOf(currentFrame));
    }

    /**
     * Die routine is a method that handles the death of an enemy.
     */
    public void dieRoutine() {
        if (System.currentTimeMillis() - dieTime < 100) {
            PhysicsEngine.applyGravity(this);
            setY(getY() + getVelocityY());
            setX(getX() + getVelocityX());
            if (getY() > Config.SCREEN_HEIGHT) setY(0);
            if (getX() > Config.SCREEN_WIDTH) setX(0);
            if (getX() < 0) setX(Config.SCREEN_WIDTH);
            if (getY() < 0) setY(Config.SCREEN_HEIGHT);
        } else {
            boolean isOnPlatform = level.getWallsCollection().getNearWalls(this).stream()
                    .anyMatch(wall -> wall.isPlatform() && CollisionHandler.isCollidingWithTopOfWall(this, wall) && !CollisionHandler.isCollidingWithSideOfWall(this, wall));

            if (isOnPlatform) {
                setVelocityY(0);
                setVelocityX(0);
                PowerUp.PowerUpType type;
                if (this instanceof ZenChan) {
                    type = PowerUp.PowerUpType.APPLE;
                } else if (this instanceof Monster) {
                    type = PowerUp.PowerUpType.GRAPE;
                } else {
                    type = PowerUp.PowerUpType.CHERRY;
                }
                PowerUp powerUp = PowerUpFactory.createPowerUp(getX(), getY(), level, type);
                level.getEntitiesCollection().add(powerUp);
                level.getEntitiesCollection().remove(this);
            } else {
                PhysicsEngine.applyGravity(this);
                setX(getX() + getVelocityX());
                if (getY() > Config.SCREEN_HEIGHT) setY(0);
                else if (getY() < 0) setY(Config.SCREEN_HEIGHT);
                if (getX() > Config.SCREEN_WIDTH) setX(0);
                else if (getX() < 0) setX(Config.SCREEN_WIDTH);
            }
        }
    }

    /**
     * Sets vulnerable.
     *
     * @param b the b
     */
    public void setVulnerable(boolean b) {
        vulnerable = b;
        if (b) {
            state = facingRight ? EnemyState.VULNERABLE_RIGHT : EnemyState.VULNERABLE_LEFT;
        } else {
            state = facingRight ? EnemyState.FACING_RIGHT : EnemyState.FACING_LEFT;
        }
    }

    /**
     * Sets vulnerable time.
     *
     * @param time the time
     */
    public void setVulnerableTime(long time) {
        vulnerableTime = time;
    }

    /**
     * Stop.
     */
    protected void stop() {
        setVelocityX(0);
        setVelocityY(0);
        setX(getX());
        setY(getY());
    }

    /**
     * Vulnerable routine.
     */
    protected void vulnerableRoutine() {
        if (System.currentTimeMillis() - vulnerableTime > Config.VULNERABLE_TIME) {
            vulnerable = false;
            state = facingRight ? EnemyState.FACING_RIGHT : EnemyState.FACING_LEFT;
        } else {
            stop();
        }
    }

    /**
     * The enum Enemy state.
     */
    public enum EnemyState {
        /**
         * Facing right enemy state.
         */
        FACING_RIGHT,
        /**
         * Facing left enemy state.
         */
        FACING_LEFT,
        /**
         * Vulnerable right enemy state.
         */
        VULNERABLE_RIGHT,
        /**
         * Vulnerable left enemy state.
         */
        VULNERABLE_LEFT,
        /**
         * Is in prison enemy state.
         */
        IS_IN_PRISON,
        /**
         * Die enemy state.
         */
        DIE;
    }


}

