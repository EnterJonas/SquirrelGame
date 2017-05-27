package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class BadBeast extends Character {

    private static final int ENERGY = -150;
    private static final int VISION = 6;
    private int bites;

    public BadBeast(EntityType entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
        this.bites = 7;
    }

    @Override
    public void nextStep(EntityContext context) {
        if (!isStunned()) {
            //get random next position
            XY nextPosition = new XYsupport().getNewPosition(this.getPosition());
            //check random next position
            context.tryMove(this,nextPosition);
            this.setSuspensionCounter(4);
        }else{
            //slowly wake up
            this.setSuspensionCounter(-1);
        }
    }

    public int getRemainingBites(){
        return this.bites;
    }

    public void setAmountBites(int amount){
        this.bites += amount;
    }

    public int getVision(){
        return VISION;
    }

    public String toString() {
        return "BadBeast";
    }

}
