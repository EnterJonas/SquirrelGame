package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;

public class GoodBeast extends Movable {

    private static final int ENERGY = 200;
    private static final int VISION = 6;
    private int sleepTimer;


    public GoodBeast(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context) {
        if (!isSleeping()) {
            //get random next position
            XY nextPosition = this.getPosition().getNewPosition();
            //check random next position
            context.tryMove(this,nextPosition);
            sleep();
        }else{
            //slowly wake up
            wake();
        }
    }

    public int getVision(){
        return VISION;
    }

    private void sleep(){
        this.sleepTimer = 0;
    }

    private boolean isSleeping() {
        return sleepTimer != 4;
    }

    private void wake(){
        this.sleepTimer++;
    }

    public String toString() {
        return "G";
    }

}
