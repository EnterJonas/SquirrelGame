package de.hsa.games.fatsquirrel.util;

import java.util.Random;

public class XYsupport {

    public XY getNewPosition(XY xy) {
        Random random = new Random();
        int currentY = xy.getY();
        int currentX = xy.getX();
        return new XY(currentY + random.nextInt(1 + 1 + 1) - 1, currentX + random.nextInt(1 + 1 + 1) - 1);
    }

    //subtracts one position from another
    public XY createVector(XY peak, XY foot) {
        return new XY(peak.getY() - foot.getY(), peak.getX() - foot.getX());
    }

    public XY createMovementVector(XY xy) {
        int x = 0;
        int y = 0;
        if (xy.getY() > 0) {
            y = 1;
        } else if (xy.getY() < 0) {
            y = -1;
        }
        if (xy.getX() > 0) {
            x = 1;
        } else if (xy.getX() < 0) {
            x = -1;
        }
        return new XY(y, x);
    }

    public XY setNewPosition(XY current, XY xy) {
        return new XY(current.getY() + xy.getY(), current.getX() + xy.getX());
    }

    public XY getRandomPositionInWorld(XY xy) {
        Random random = new Random();
        int randomY = random.nextInt((xy.getY() - 2)) + 1;
        int randomX = random.nextInt((xy.getX() - 2)) + 1;
        return new XY(randomY, randomX);
    }
}
