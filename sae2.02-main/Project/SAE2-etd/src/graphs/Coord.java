package graphs;
import java.lang.Math;
public class Coord {
    private double x, y;
    public Coord(){
        x = y = 0.;
    }
    public Coord(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double dist(Coord c){
        return (Math.sqrt(Math.abs(Math.pow(x-y, 2.0)+Math.pow(c.x-c.y, 2.0))));
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    
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
}
