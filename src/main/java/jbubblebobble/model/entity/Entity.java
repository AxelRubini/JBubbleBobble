package jbubblebobble.model.entity;
import jbubblebobble.model.CollisionHandler;
import jbubblebobble.model.level.Level;
import jbubblebobble.model.level.Wall;
import utility.Config;

import java.util.List;
import java.util.Observable;

/**
 * Entity class is the superclass of all the entities in the game
 */
public abstract class Entity extends Observable {

    /**
     * The Coordinate.
     */
    protected Coordinate coordinate;
    /**
     * The Frames.
     */
    protected List<Integer> frames;
    /**
     * The Current frame.
     */
    protected int currentFrame;
    /**
     * The Last frame time.
     */
    protected long lastFrameTime;
    /**
     * The constant JUMP_STRENGTH.
     */
    protected static final double JUMP_STRENGTH = Config.JUMP_STRENGTH;
    /**
     * The Velocity x.
     */
    protected double velocityX;
    /**
     * The Velocity y.
     */
    protected double velocityY;
    /**
     * The Width.
     */
    protected double width;
    /**
     * The Height.
     */
    protected double height;
    /**
     * The Facing right.
     */
    protected boolean facingRight;
    /**
     * The Is moving.
     */
    protected boolean isMoving;
    /**
     * The Level.
     */
    protected Level level;

    /**
     * Instantiates a new Entity.
     *
     * @param x     the x
     * @param y     the y
     * @param level the level
     */
    public Entity(double x, double y,Level level) {
        coordinate = new Coordinate(x, y);
        this.lastFrameTime = System.currentTimeMillis();
        this.level = level;
        this.width = 16;
        this.height = 16;

    }

    /**
     * Check entity collision.
     */
    protected void checkEntityCollision() {
        for (int i = 0; i < level.getEntitiesCollection().getEntitiesSortedByDistance(this).size(); i++) {
            if (level.getEntitiesCollection().getEntitiesSortedByDistance(this).size() > i){
                Entity entity = level.getEntitiesCollection().getEntitiesSortedByDistance(this).get(i);
                if (CollisionHandler.isColliding(this, entity)) {
                    handleEntityCollision(entity);
                }
            }
        }
    }

    /**
     * Handle entity collision performs routines when an entity collides with another entity.
     * The routine is different for each entity.
     * The method is abstract and must be implemented by the subclasses.
     *
     * @param entity the entity
     */
    protected abstract void handleEntityCollision(Entity entity);

    /**
     * Handle wall collision performs routines when an entity collides with a wall.
     * The routine is different for each entity.
     * The method is abstract and must be implemented by the subclasses.
     *
     * @param wall the wall
     */
    protected abstract void handleWallCollision(Wall wall);

    /**
     * Check wall collision.
     */
    protected  void checkWallCollision() {
        for (Wall wall : level.getWallsCollection().getNearWalls(this)) {
            if (CollisionHandler.isCollidingWithWall(this, wall)) {
                handleWallCollision(wall);
            }
        }
    }

    /**
     * if the entity is out of the screen, it will be placed on the other side of the screen
     */
    protected void handlePacManEffect(){
        if (getY() > Config.SCREEN_HEIGHT) {
            setY(0);
        }
    }

    /**
     * Update frame of the entity based on a timer to create the animation
     */
    protected void updateFrame() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= Config.FRAME_DURATION) {
            currentFrame = (currentFrame + 1) % frames.size();
            lastFrameTime = currentTime;
        }
    }

    /**
     * Returns the information of the entity with a List of Strings
     * the List contains the x coordinate, the y coordinate, the state of the entity and the current frame and other information if necessary
     * the method is abstract and must be implemented by the subclasses.
     *
     * @return the info
     */
    public abstract List<String> getInfo ();

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX () {
        return coordinate.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY () {
        return coordinate.getY();
    }

    /**
     * Set x.
     *
     * @param x the x
     */
    public void setX ( double x){
        coordinate.setX(x);
    }

    /**
     * Set y.
     *
     * @param y the y
     */
    public void setY ( double y){
        coordinate.setY(y);
    }

    /**
     * Move.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void move ( double dx, double dy){
        coordinate.applyCoordinate(dx, dy);
    }

    /**
     * Gets coordinate.
     *
     * @return the coordinate
     */
    public Coordinate getCoordinate () {
        return coordinate;
    }

    /**
     * Set coordinate.
     *
     * @param coordinate the coordinate
     */
    public void setCoordinate (Coordinate coordinate){
        this.coordinate = coordinate;
    }

    /**
     * Gets velocity y.
     *
     * @return the velocity y
     */
    public double getVelocityY () {
        return velocityY;
    }

    /**
     * Set velocity y.
     *
     * @param velocityY the velocity y
     */
    public void setVelocityY ( double velocityY){
        this.velocityY = velocityY;
    }

    /**
     * Gets velocity x.
     *
     * @return the velocity x
     */
    public double getVelocityX () {
        return velocityX;
    }

    /**
     * Set velocity x.
     *
     * @param velocityX the velocity x
     */
    public void setVelocityX ( double velocityX){
        this.velocityX = velocityX;
    }

    /**
     * Update method notifies the observers of the entity
     * and performs the update of the entity checking collisions and updating the frame
     * the method is abstract and must be implemented by the subclasses.
     */
    public abstract void update ();

    /**
     * Set moving.
     *
     * @param b the b
     */
    public void setMoving ( boolean b){
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth () {
        return width;
    }

    /**
     * Set width.
     *
     * @param width the width
     */
    public void setWidth ( double width){
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight () {
        return height;
    }

    /**
     * Set height.
     *
     * @param height the height
     */
    public void setHeight ( double height){
        this.height = height;
    }

    /**
     * Gets current frame.
     *
     * @return the current frame
     */
    public int getCurrentFrame () {
        return currentFrame;
    }

    /**
     * Set frame.
     *
     * @param frame the frame
     */
    public void setFrame ( int frame){
        this.currentFrame = frame;

    }

    /**
     * Sets frames.
     *
     * @param frames the frames
     */
    public void setFrames (List < Integer > frames) {
        this.frames = frames;
    }

    /**
     * Gets frames.
     *
     * @return the frames
     */
    public List<Integer> getFrames () {
        return frames;
    }

    /**
     * Sets current frame.
     *
     * @param currentFrame the current frame
     */
    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Stop fall.
     */
    public void stopFall(){
        velocityY = 0;
        velocityX = 0;
    }

    /**
     * Is facing right boolean.
     *
     * @return the boolean
     */
    public boolean isFacingRight(){
        return facingRight;
    }

    /**
     * Is die boolean.
     *
     * @return the boolean
     */
    public boolean isDie() {
        return false;
    }


    }
