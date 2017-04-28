package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.interfaces.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

public class GoodBeast extends Movable {

    private static final int ENERGY = 200;
    private static final int VISION = 6;
    private EntityContext context;
    private int sleepTimer = 0;


    public GoodBeast(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context, XY moveDirection) {
        this.context = context;
        this.sleepTimer++;
        if (!isSleeping()) {
            if (isSquirrelAround(context, VISION)) {
                runAway();
            } else {
                runAround(context);
            }
            this.sleepTimer = 0;
        }
    }

    private boolean isSleeping() {
        return sleepTimer != 4;
    }

    private void runAway() {
        XY temp = this.getPosition().createMovementVector(getNextSquirrel().getPosition().createVector(this.getPosition()));
        XY temp2 = new XY(temp.getY() * -1, temp.getX() * -1);
        XY temp3 = new XY(temp.getY() + temp2.getY(), temp.getX() + temp2.getX());
        Entity intersectingEntity = context.getEntitySet().getSet().getIntersectingObject(this.getPosition(), this);

        //if nothing is intersecting at position
        if (!(context.getEntitySet().getSet().isIntersecting(temp3))) {
            updatePosition(temp3);
        }
        //if GoodBeast somehow has same location as Squirrel remove and replace
        else if (intersectingEntity instanceof Squirrel) {
            context.killAndReplace(this);
        }
        //if something is in the way
        else {
            runAround(context);
        }
    }

    public String toString() {
        return "G";
    }

}
