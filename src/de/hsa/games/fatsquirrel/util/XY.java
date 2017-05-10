package de.hsa.games.fatsquirrel.util;

import java.util.Random;

public class XY {

    private final int x;
    private final int y;

    public XY(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public XY setNewPosition( XY vector) {
        return new XY(this.getY() + vector.getY(), this.getX()+vector.getX());
    }

    public XY getNewPosition() {
        Random random = new Random();
        int currentY = this.getY();
        int currentX = this.getX();
        return new XY(currentY + random.nextInt(1 + 1 + 1) - 1, currentX + random.nextInt(1 + 1 + 1) - 1);
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

    public XY createMovementVector(XY vector){
        int x = 0;
        int y = 0;
        if(vector.getY() > 0){
            y = 1;
        }else if(vector.getY() < 0){
            y = -1;
        }
        if(vector.getX() > 0){
            x = 1;
        }else if (vector.getX() < 0){
            x = -1;
        }
        return new XY (y,x);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public XY getRandomPositionInWorld(XY size){
        Random random = new Random();
        int randomY = random.nextInt((size.getY() - 2)) + 1;
        int randomX = random.nextInt((size.getX() - 2)) + 1;
        return new XY(randomY, randomX);
    }

    @Override
    public String toString() {
        return "x= " + this.getX() + '\n'
                + "y= " + this.getY();
    }
}