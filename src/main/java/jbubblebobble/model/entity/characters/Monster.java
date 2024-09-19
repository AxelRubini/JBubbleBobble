package jbubblebobble.model.entity.characters;

import jbubblebobble.model.level.Level;
import jbubblebobble.model.level.Wall;
import utility.Config;

/**
 * Represents a Monster character in the game.
 */
public class Monster extends Enemy {
    private char symbol = 'M';
    private double velocityY = 1;

    /**
     * Constructs a Monster instance.
     *
     * @param x     the x-coordinate of the monster
     * @param y     the y-coordinate of the monster
     * @param level the level in which the monster exists
     */
    public Monster(double x, double y, Level level) {
        super(x, y, level);
        init();
    }

    private void init(){
        facingRight = true;
        state = EnemyState.FACING_RIGHT;
        speed = 1;
        ableToFly = true;
    }

    /**
     * Defines the AI movement behavior of the monster.
     */
    @Override
    protected void moveAI() {
        if (!caughtByBubble) {
            if (coordinate.distance(level.getPlayer().getCoordinate()) < 100) {
                moveTowardsPlayer();
            } else {
                if (facingRight) {
                    setX(getX() + speed);
                } else {
                    setX(getX() - speed);
                }
                setY(getY() + velocityY);
            }
        } else {
            velocityY = -0.5;
            setY(getY() + velocityY);
        }
    }
    @Override
    protected void handlePacManEffect(){
        super.handlePacManEffect();
        if (getY()<0){
            setY(Config.SCREEN_HEIGHT);
        }
    }
    /**
     * Handles the collision of the monster with a wall.
     *
     * @param wall the wall with which the monster collides
     */
    @Override
    protected void handleWallCollision(Wall wall) {
        double monsterLeft = getX();
        double monsterRight = getX() + getWidth();
        double monsterTop = getY();
        double monsterBottom = getY() + getHeight();

        double wallLeft = wall.getCoordinate().getX();
        double wallRight = wall.getCoordinate().getX() + wall.getWidth();
        double wallTop = wall.getCoordinate().getY();
        double wallBottom = wall.getCoordinate().getY() + wall.getHeight();

        if (!caughtByBubble) {
            if (monsterBottom > wallTop && monsterTop < wallTop) {
                // Collision from the top
                setY(wallTop - getHeight());
                velocityY = -velocityY;
            } else if (monsterRight > wallLeft && monsterLeft < wallLeft) {
                // Collision from the left
                setX(wallLeft - getWidth());
                setIsFacingRight(!facingRight);
                state = EnemyState.FACING_RIGHT;
            } else if (monsterLeft < wallRight && monsterRight > wallRight) {
                // Collision from the right
                setX(wallRight);
                setIsFacingRight(!facingRight);
                state = EnemyState.FACING_LEFT;
            } else if (monsterTop < wallBottom && monsterBottom > wallBottom) {
                // Collision from the bottom
                setY(wallBottom);
                velocityY = -velocityY;
            }
        } else {
            if (monsterBottom > wallTop && monsterTop < wallTop) {
                // Collision from the top
                setY(wallTop - getHeight());
            } else if (monsterTop < wallBottom && monsterBottom > wallBottom) {
                // Collision from the bottom
                setY(wallBottom);
            }
        }
    }

    /**
     * Updates the state of the monster.
     */
    @Override
    public void update() {
        if (die) {
            dieRoutine();
        } else if (vulnerable) {
            vulnerableRoutine();
        } else {
            moveAI();
            handlePacManEffect();
            checkWallCollision();
        }
        updateFrame();
        setChanged();
        notifyObservers();
    }
}