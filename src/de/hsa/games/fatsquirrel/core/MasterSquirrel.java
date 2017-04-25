package de.hsa.games.fatsquirrel.core;


public abstract class MasterSquirrel extends Squirrel {

    public MasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }

    public String toString(){
        return "S";
    }
}
