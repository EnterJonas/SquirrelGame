package de.hsa.games.fatsquirrel.util;

public class XY {

    private final int x;
    private final int y;

    public static final XY ZERO_ZERO = new XY(0, 0);
    public static final XY RIGHT = new XY(1, 0);
    public static final XY LEFT = new XY(-1, 0);
    public static final XY UP = new XY(0, -1);
    public static final XY DOWN = new XY(0, 1);
    public static final XY RIGHT_UP = new XY(1, -1);
    public static final XY RIGHT_DOWN = new XY(1, 1);
    public static final XY LEFT_UP = new XY(-1, -1);
    public static final XY LEFT_DOWN = new XY(-1, 1);

    public XY(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public XY plus(int y, int x) {
        return new XY(this.x + x, this.y + y);
    }

    public XY minus(XY xy) {
        return new XY(this.getY() - xy.getY(), this.getX() - xy.getX());
    }

    public XY times(){
        return null;
    }

    public double length(){
        return 0;
    }

    /**
     * @param xy a second coordinate pair
     * @return the euklidian distance (pythagoras)
     */
    public double distanceFrom(XY xy) {
        return Math.round(Math.sqrt(Math.pow(xy.getX() - this.getX(), 2) + Math.pow(xy.getY() - this.getY(), 2)));
    }

    @Override
    public int hashCode(){
        return 0;
    }

    @Override
    public boolean equals(Object obj){
        return false;
    }

    @Override
    public String toString() {
        return "x= " + this.getX() + '\n' + "y= " + this.getY();
    }

    //TODO how am I supposed to access these when they're set private??
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}