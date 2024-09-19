package jbubblebobble.model.entity;

/**
 * Class Coordinate encapsulates the x and y coordinates of an entity.
 * It provides methods to get and set the x and y coordinates, apply a coordinate and calculate the distance between two coordinates.
 */
public class Coordinate {
    private double x, y;

    /**
     * Instantiates a new Coordinate.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Apply coordinate.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void applyCoordinate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
    public double distance(Coordinate other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}