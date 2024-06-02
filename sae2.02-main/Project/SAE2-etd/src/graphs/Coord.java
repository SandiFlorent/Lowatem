package graphs;

public class Coord {

    /**
     * The coordinates on two axis, x and y
     */
    private double x, y;

    /**
     * A constructor that'll set the coordinates to 0
     */
    public Coord() {
        x = y = 0.;
    }

    /**
     * A constructor that'll set the coordinates to the designed values
     *
     * @param x the value to set to the x coordinate
     * @param y the value to set to the y coordinate
     */
    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function calculate the distance between two coordinates
     *
     * @param c to coordinate which we want to calculate the distance with
     * @return the distance
     */
    public double dist(Coord c) {
        return Math.sqrt(Math.pow(this.x - c.x, 2) + Math.pow(this.y - c.y, 2));
    }

    /**
     * This function returns the x coordinate
     *
     * @return the x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * This function returns the y coordinate
     *
     * @return the y coordiate
     */
    public double getY() {
        return y;
    }

    /**
     * This function sets the x coordinate
     *
     * @param x the new value for the x coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * This function sets the y coordinate
     *
     * @param y the new value for the x coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Coord c = (Coord) o;
        return x == c.x && y == c.y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }
}
