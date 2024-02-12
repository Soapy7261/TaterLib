package dev.neuralnexus.taterlib.utils;

/** Represents a position in the world */
public class Position {
    private final double x;
    private final double y;
    private final double z;

    /**
     * Creates a new position.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     */
    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Getter for the x coordinate.
     *
     * @return The x coordinate.
     */
    public double x() {
        return x;
    }

    /**
     * Getter for the y coordinate.
     *
     * @return The y coordinate.
     */
    public double y() {
        return y;
    }

    /**
     * Getter for the z coordinate.
     *
     * @return The z coordinate.
     */
    public double z() {
        return z;
    }
}
