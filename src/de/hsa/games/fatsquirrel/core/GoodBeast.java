package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;

public class GoodBeast extends Character {

    private static final int ENERGY = 200;
    private static final int VISION = 6;

    public GoodBeast(EntityType entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context) {
        if (!isStunned()) {
            //get random next position
            XY nextPosition = this.getPosition().getNewPosition();
            //check random next position
            context.tryMove(this,nextPosition);
            setSuspensionCounter(4);
        }else{
            //slowly wake up
            setSuspensionCounter(-1);
        }
    }

    public int getVision(){
        return VISION;
    }

    public String toString() {
        return "GoodBeast";
    }

}
