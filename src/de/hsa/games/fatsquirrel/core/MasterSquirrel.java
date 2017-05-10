package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;

public abstract class MasterSquirrel extends Squirrel {

    public MasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }

    public boolean isParent(MiniSquirrel miniSquirrel){
        return miniSquirrel.getParent().equals(this);
    }

    public String toString(){
        return "S";
    }
}
