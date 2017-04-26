package de.hsa.games.fatsquirrel.core;

import java.util.Random;

public class XY {

    private final int x;
    private final int y;

    public XY(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public XY setNewPosition(XY location) {
        return new XY(location.getY(), location.getX());
    }

    public XY getNewPosition(XY currentLocation) {
        Random random = new Random();
        int currentY = currentLocation.getY();
        int currentX = currentLocation.getX();
        return new XY(currentY + random.nextInt(1 + 1 + 1) - 1, currentX + random.nextInt(1 + 1 + 1) - 1);
    }

    //returns random moveDirection for (Good- and BadBeast as well as for Mini- and BotSquirrel)
    public XY getMoveDirection() {
        Random random = new Random();
        return new XY(random.nextInt(1 + 1 + 1) - 1, random.nextInt(1 + 1 + 1) - 1);
    }

    //subtracts one position from another
    public XY createVector(XY bottom) {
        return new XY(this.getY() - bottom.getY(), this.getX() - bottom.getX());
    }

    //returns positive integer indicating the distance between two positions
    public int getSteps(XY bottom) {
        XY vector = createVector(bottom);
        XY temp1 = new XY(0, 0);
        if (vector.getX() < 0) {
            temp1 = new XY(vector.getY(), (vector.getX() * -1));
            if (vector.getY() < 0) {
                temp1 = new XY((vector.getY() * -1), temp1.getX());
            }
        } else if (vector.getY() < 0) {
            temp1 = new XY((vector.getY() * -1), vector.getX());
            if (vector.getX() < 0) {
                temp1 = new XY(vector.getY(), (temp1.getX() * -1));
            }
        }
        int steps = temp1.getY() + temp1.getX();
        if (steps == 0)
            return vector.getY() + vector.getX();
        else
            return steps;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x= " + this.getX() + '\n'
                + "y= " + this.getY();
    }
}