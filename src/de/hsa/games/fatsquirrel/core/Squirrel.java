package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;

public abstract class Squirrel extends Movable {

    private int suspensionCounter = 0;

    public Squirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }

    public boolean isStunned(){
        return getSuspensionCounter() > 0;
    }

    public int getSuspensionCounter() {
        return suspensionCounter;
    }

    public void setSuspensionCounter(int amount){
        this.suspensionCounter += amount;
    }

}
