package jbubblebobble.model.level;

import jbubblebobble.model.entity.Coordinate;
import utility.Config;

import java.util.List;

/**
 * Wall class represents the walls in the game.
 */
public class Wall {

    private Coordinate coordinate;
    private double width;
    private double height;
    private boolean platform;
    private int levelNumber;

    /**
     * Instantiates a new Wall.
     *
     * @param x           the x
     * @param y           the y
     * @param isPlatform  the is platform
     * @param levelNumber the level number
     */
    public Wall(double x,double y,boolean isPlatform,int levelNumber) {
        this.coordinate = new Coordinate(x,y);
        this.width = 18;
        this.height = 18;
        this.platform = isPlatform;
        this.levelNumber = levelNumber;
    }

    /**
     * returns the information of the wall to be displayed
     * by the view
     *
     * @return the list
     */
    public List<String> getInfo(){
        return List.of(String.valueOf(coordinate.getX()), String.valueOf(coordinate.getY()),String.valueOf(levelNumber));
    }

    /**
     * Is platform boolean.
     *
     * @return the boolean
     */
    public boolean isPlatform() {
        return platform;
    }

    /**
     * Gets coordinate.
     *
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return coordinate.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return coordinate.getY();
    }
}
