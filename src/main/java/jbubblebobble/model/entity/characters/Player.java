package jbubblebobble.model.entity.characters;


import jbubblebobble.model.PhysicsEngine;
import jbubblebobble.model.entity.Coordinate;
import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.PlatformDetection;
import jbubblebobble.model.entity.bubble.Bubble;
import jbubblebobble.model.entity.powerup.PowerUp;
import jbubblebobble.model.level.Wall;
import jbubblebobble.model.level.Level;
import utility.Config;

import java.util.*;

/**
 * Player class.
 */
public class Player extends Entity implements PlatformDetection {
    private static Player instance;
    private int lives;
    private int score;
    private double speed;
    private boolean jumping;
    private boolean firing;
    private double firingTime;
    private PlayerState state;
    private boolean ableToJump;
    private boolean unkillable;
    private double startX;
    private double startY;
    private boolean powerUpMoving;
    private boolean powerUpPurpleGum;
    private boolean powerUpJumping;
    private boolean powerUpFire;
    private long lastFireTime;
    private int bubbleBlown;
    private int bubbleExploded;
    private int jumpCount;
    private int blueGumCount;
    private int purpleGumCount;
    private int yellowGumCount;
    private int killedEnemies;
    private double distance;
    private boolean alive;
    private int audio;
    private List<Observer> observers;
    private long lastJumpTime;

    /**
     * Instantiates a new Player.
     *
     * @param x     the x
     * @param y     the y
     * @param level the level
     */
    private Player(double x, double y, Level level) {
        super(x, y,level);
        init();
        addObserver(level);
    }

    /**
     * Get instance player.
     *
     * @param x     the x
     * @param y     the y
     * @param level the level
     * @return the player
     */
    public static Player getInstance(double x, double y, Level level){
        if (instance == null){
            instance = new Player(x,y,level);
        }
        return instance;
    }

    /**
     * Intialize the player at the start of  a level
     *
     *
     */
    private void init(){
        lives = 3;
        score = 0;
        speed = Config.PLAYER_SPEED;
        frames = List.of(0, 1);
        state = PlayerState.IDLE_RIGHT;
        velocityY = 0;
        width = 16;
        height = 16;
        startX = getX();
        startY = getY();
        alive = true;
        facingRight = true;
        observers = new ArrayList<>();
        addObserver(level);

    }


    @Override
    protected void handleEntityCollision(Entity entity) {
        if (state == PlayerState.DIE || !alive) return;
        if (entity instanceof Bubble bubble){
                ableToJump = true;

        } else if (entity instanceof Enemy enemy){
            if ((enemy.isCaughtByBubble()  || enemy.isVulnerable()) && !enemy.isDie()) {
                if (enemy.isCaughtByBubble() && jumping) {
                    audio =2;
                    notifyAudio(observers.get(0));
                    List<Bubble> nearBubbles = level.getEntitiesCollection().getNearBubbles(this);
                    for (Bubble bubble : nearBubbles) {
                        bubble.setPopping(true);
                    }
                    List<Enemy> nearEnemies = level.getEntitiesCollection().getNearEnemiesCaughtByBubble(enemy);
                    int multiplier = 2;
                    for (int i = 1; i < nearEnemies.size(); i++) {
                        nearEnemies.get(i).setDie(true);
                        score += 100 * multiplier;
                        multiplier++;
                        killedEnemies++;
                    };
                    score += 100;
                    enemy.setDie(true);
                    killedEnemies++;
                }else{
                    enemy.setX(enemy.getX() + (facingRight ? speed : -speed) );
                }

            } else if ( !enemy.isDie() && alive) {
                alive = false;
                loseLife();
            }
        } else if (entity instanceof PowerUp && ((PowerUp) entity).isActive()){
            audio = 3;
            notifyAudio(observers.get(0));

        }
    }

    /**
     * the control of the jump is inside the update method because the jump is a continuous action
     * and not a one-time action therefore it needs to be updated
     * every update cycle
     *
     */
    @Override
    public void update() {
        if (firing){
            firingTime -= 0.016;
            if (firingTime <= 0){
                firing = false;
                setState(facingRight ? PlayerState.IDLE_RIGHT : PlayerState.IDLE_LEFT);
            }
        }
        if (jumping) {
            PhysicsEngine.applyGravity(this);
            setY(getY() + velocityY); // Apply vertical velocity
            setX(getX() + velocityX); // Apply horizontal velocity
        }
        if (!isOnPlatform(level,this) && state != PlayerState.DIE) {
            setVelocityY(getVelocityY() + Config.GRAVITY);

            move(0, getVelocityY());
            handlePacManEffect();
        }
        if (state != PlayerState.DIE && !unkillable) {
            checkEntityCollision();

        }
        checkWallCollision();
        setCoordinate(new Coordinate(getX(), getY()));
        updateFrame();
        setChanged();
        notifyObservers(this);
    }

    protected void handleWallCollision(Wall wall) {
        if (wall.isPlatform()&& jumping && velocityY <0) {
            return;
        }

        double playerLeft = getX();
        double playerRight = getX() + getWidth();
        double playerTop = getY();
        double playerBottom = getY() + getHeight();

        double wallLeft = wall.getX();
        double wallRight = wall.getX() + wall.getWidth();
        double wallTop = wall.getY();
        double wallBottom = wall.getY() + wall.getHeight();

        double overlapLeft = playerRight - wallLeft;
        double overlapRight = wallRight - playerLeft;
        double overlapTop = playerBottom - wallTop;
        double overlapBottom = wallBottom - playerTop;

        // Determine the smallest overlap to resolve the collision
        double minOverlap = Math.min(Math.min(overlapLeft, overlapRight), Math.min(overlapTop, overlapBottom));

        if (minOverlap == overlapLeft) {
            setX(wallLeft - getWidth());
        } else if (minOverlap == overlapRight) {
            setX(wallRight);
        } else if (minOverlap == overlapTop) {
            setY(wallTop - getHeight());
        } else if (minOverlap == overlapBottom) {
            setY(wallBottom);
        }

        // Stop the player's movement in the direction of the collision
        if (minOverlap == overlapLeft || minOverlap == overlapRight) {
            setVelocityY(Config.MAX_FALL_SPEED);
            setVelocityX(0);
        } else if (minOverlap == overlapTop || minOverlap == overlapBottom) {
            jumping = false;
            setVelocityY(0);
    }
}

    /**
     * Move left.
     */
    public void moveLeft() {
        if (state == PlayerState.DIE || !alive) return;
        state = PlayerState.MOVING_LEFT;
        setX(getX() - speed);
        facingRight = false;
        isMoving = true;
        distance += speed;
        if (powerUpMoving){score += 10;}
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (state == PlayerState.DIE || !alive) return;
        state = PlayerState.MOVING_RIGHT;
        setX(getX() + speed);
        facingRight = true;
        isMoving = true;
        distance += speed;
        if (powerUpMoving){score += 10;}
    }
    /**
     * Check if enough time has passed since the last jump
     */
    private boolean canJump(){
        return System.currentTimeMillis() - lastJumpTime > Config.JUMP_RATE;
    }
    /**
     * The player jump vertically
     *
     * @return the boolean
     */
    public boolean verticalJump(){
        if (state == PlayerState.DIE || !alive) return false;
        if(!canJump()) return false;
        if(isOnPlatform(level,this) || ableToJump){
            velocityY = JUMP_STRENGTH;
            jumping = true;
            velocityX =0;
            setState(facingRight ? PlayerState.JUMPING_RIGHT : PlayerState.JUMPING_LEFT);
            ableToJump = false;
            jumpCount++;
            if (powerUpJumping){score += 500;}
            lastJumpTime = System.currentTimeMillis();
            return  true;
        }
        return false;
    }

    /**
     * The player jump with a direction
     *
     * @param toRight the to right
     * @return the boolean
     */
    public boolean jump(boolean toRight) {
        if (state == PlayerState.DIE || !alive) return false;
        if(!canJump()) return false;
        if(isOnPlatform(level,this)|| !jumping){
            velocityY = JUMP_STRENGTH;
            jumping = true;
            velocityX = toRight ? 0.5: -0.5;
            setState(facingRight ? PlayerState.JUMPING_RIGHT : PlayerState.JUMPING_LEFT);
            ableToJump = false;
            jumpCount++;
            if (powerUpJumping){score += 500;}
            lastJumpTime = System.currentTimeMillis();
            return  true;
        }
        return false;
    }

    /**
     * Fire boolean.
     *
     * @return the boolean
     */
    public boolean fire() {
        if (state == PlayerState.DIE ||  !alive) return false;
        if (System.currentTimeMillis() - lastFireTime > (powerUpFire ? Config.FIRE_RATE / 2 : Config.FIRE_RATE)) {
            setFiring(true);
            double bubbleX = (facingRight) ? getX() + getWidth() : getX()- getWidth();
            double bubbleY = getY() + getHeight() / 4.0;
            Bubble bubble = new
                    Bubble(bubbleX, bubbleY, facingRight,level, powerUpPurpleGum ? Config.BUBBLE_FLY_TIME * 2 : Config.BUBBLE_FLY_TIME);
            level.getEntitiesCollection().add(bubble); // Add the bubble to the level
            lastFireTime = System.currentTimeMillis();
            if (powerUpFire){score += 100;}
            bubbleBlown++;
            return true;
        }
        return false;

    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public PlayerState getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(PlayerState state) {
        this.state = state;
    }

    /**
     * Is jumping boolean.
     *
     * @return the boolean
     */
    public boolean isJumping() {
        return jumping;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    /**
     * Sets facing right.
     *
     * @param facingRight the facing right
     */
    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    /**
     * Sets firing.
     *
     * @param firing the firing
     */
    public void setFiring(boolean firing)
    {
        this.firing = true;
        state = facingRight ? PlayerState.FIRING_RIGHT : PlayerState.FIRING_LEFT;
        this.firingTime = firing ? 0.5 : 0.0;

    }

    /**
     * Get moving boolean.
     *
     * @return the boolean
     */
    public boolean getMoving(){
        return isMoving;
    }

    /**
     * Get speed double.
     *
     * @return the double
     */
    public double getSpeed(){
        return speed;
    }

    @Override
    public boolean isDie(){
        return lives == 0;
    }

    public List<String> getInfo(){
        return List.of(String.valueOf(getX()), String.valueOf(getY()), state.toString(), String.valueOf(currentFrame),
                String.valueOf(lives), String.valueOf(score));
    }

    /**
     * Lose life.
     */
    public void loseLife(){
        state = PlayerState.DIE;
        lives--;
        jumping = false;
        velocityX = 0;
        velocityY = 0;
        frames = List.of(0,1,2,3);
        alive = false;
        audio = 1;
        notifyAudio(observers.get(0));
        if (lives == 0){
            level.gameOver();
            System.out.println("Game Over       " + "Print Metodo LoseLife Player");
        }else{
            new Timer().schedule(new TimerTask() {
                /**
                 * The action to be performed by this timer task.
                 */
                @Override
                public void run() {
                    frames = List.of(0,1);
                    currentFrame =0;
                    respawn();
                    alive = true;
                }
            },1000);
        }
    }

    /**
     * Stop moving.
     */
    public void stopMoving(){
        setVelocityX(0);
        if (getState() == PlayerState.MOVING_LEFT) {
            setState(PlayerState.IDLE_LEFT);
        } else if (getState() == PlayerState.MOVING_RIGHT) {
            setState(PlayerState.IDLE_RIGHT);
        }
    }

    /**
     * Set speed.
     *
     * @param speed the speed
     */
    public void setSpeed(double speed){
        this.speed = speed;
    }

    /**
     * Set lives.
     *
     * @param lives the lives
     */
    public void setLives(int lives){
        this.lives = lives;
    }

    /**
     * Set score.
     *
     * @param score the score
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * Respawn.
     */
    public void respawn(){
        currentFrame = 0;
        setCoordinate(new Coordinate(startX,startY));
        setVelocityX(0);
        setVelocityY(0);
        frames = List.of(0,1);
        setState(PlayerState.IDLE_RIGHT);
        unkillable = true;
        new Timer().schedule(new TimerTask() {
            /**
             * The action to be performed by this timer task.
             */
            @Override
            public void run() {
                unkillable = false;
            }
        },3000);

    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Set power up moving.
     *
     * @param b the b
     */
    public void setPowerUpMoving(boolean b){
        powerUpMoving = b;
    }

    /**
     * Sets power up purple gum.
     *
     * @param b the b
     */
    public void setPowerUpPurpleGum(boolean b) {
        powerUpPurpleGum = b;
    }

    /**
     * Sets power up jumping.
     *
     * @param b the b
     */
    public void setPowerUpJumping(boolean b) {
        powerUpJumping = b;
    }

    /**
     * Sets power up fire.
     *
     * @param b the b
     */
    public void setPowerUpFire(boolean b) {
        powerUpFire = b;
    }

    /**
     * Gets bubble blown.
     *
     * @return the bubble blown
     */
    public int getBubbleBlown() {
        return bubbleBlown;
    }

    /**
     * Sets bubble blown.
     *
     * @param bubbleBlown the bubble blown
     */
    public void setBubbleBlown(int bubbleBlown) {
        this.bubbleBlown = bubbleBlown;
    }

    /**
     * Gets bubble exploded.
     *
     * @return the bubble exploded
     */
    public int getBubbleExploded() {
        return bubbleExploded;
    }

    /**
     * Sets bubble exploded.
     *
     * @param bubbleExploded the bubble exploded
     */
    public void setBubbleExploded(int bubbleExploded) {
        this.bubbleExploded = bubbleExploded;
    }

    /**
     * Gets jump count.
     *
     * @return the jump count
     */
    public int getJumpCount() {
        return jumpCount;
    }

    /**
     * Sets jump count.
     *
     * @param jumpCount the jump count
     */
    public void setJumpCount(int jumpCount) {
        this.jumpCount = jumpCount;
    }

    /**
     * Gets blue gum count.
     *
     * @return the blue gum count
     */
    public int getBlueGumCount() {
        return blueGumCount;
    }

    /**
     * Sets blue gum count.
     *
     * @param blueGumCount the blue gum count
     */
    public void setBlueGumCount(int blueGumCount) {
        this.blueGumCount = blueGumCount;
    }

    /**
     * Gets purple gum count.
     *
     * @return the purple gum count
     */
    public int getPurpleGumCount() {
        return purpleGumCount;
    }

    /**
     * Sets purple gum count.
     *
     * @param purpleGumCount the purple gum count
     */
    public void setPurpleGumCount(int purpleGumCount) {
        this.purpleGumCount = purpleGumCount;
    }

    /**
     * Gets yellow gum count.
     *
     * @return the yellow gum count
     */
    public int getYellowGumCount() {
        return yellowGumCount;
    }

    /**
     * Sets yellow gum count.
     *
     * @param yellowGumCount the yellow gum count
     */
    public void setYellowGumCount(int yellowGumCount) {
        this.yellowGumCount = yellowGumCount;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets killed enemies.
     *
     * @param killedEnemies the killed enemies
     */
    public void setKilledEnemies(int killedEnemies) {
        this.killedEnemies = killedEnemies;
    }

    /**
     * Gets killed enemies.
     *
     * @return the killed enemies
     */
    public int getKilledEnemies() {
        return killedEnemies;
    }

    private void notifyAudio(Observer observer){
        observer.update(this,audio);
    }

    /**
     * update the frame of the player using the super class method
     * but if the player is dead it will update the frame with a different logic
     */
    @Override
    public void updateFrame(){
        long currentTime = System.currentTimeMillis();
        if (!isDie()){super.updateFrame();}
        else {
            if (currentTime - lastFrameTime >= Config.FRAME_DURATION){
                currentFrame++;
                lastFrameTime = currentTime;
                if (currentFrame>= frames.size()){
                    currentFrame = frames.size() - 1;
                }
            }
        }

    }

    /**
     * Set level.
     *
     * @param level the level
     */
    public void setLevel(Level level){
        this.level = level;
        deleteObservers();
        addObserver(level);
    }

    /**
     * Reset.
     */
    public void reset(){
        instance = null;
    }

    /**
     * Add audio observer.
     *
     * @param observer the observer
     */
    public void addAudioObserver(Observer observer){
        if (observers.isEmpty()){
            observers.add(observer);
        }
    }


    /**
     * The enum Player state.
     */
    public enum PlayerState {
        /**
         * Idle left player state.
         */
        IDLE_LEFT,
        /**
         * Idle right player state.
         */
        IDLE_RIGHT,
        /**
         * Moving left player state.
         */
        MOVING_LEFT,
        /**
         * Moving right player state.
         */
        MOVING_RIGHT,
        /**
         * Jumping left player state.
         */
        JUMPING_LEFT,
        /**
         * Jumping right player state.
         */
        JUMPING_RIGHT,
        /**
         * Firing left player state.
         */
        FIRING_LEFT,
        /**
         * Firing right player state.
         */
        FIRING_RIGHT,
        /**
         * Die player state.
         */
        DIE;
    }
}
