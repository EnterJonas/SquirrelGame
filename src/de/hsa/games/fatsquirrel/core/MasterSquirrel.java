package de.hsa.games.fatsquirrel.core;


public abstract class MasterSquirrel extends Squirrel {

    protected int suspensionCounter = 0;

    public MasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }


    public boolean isParent(MiniSquirrel miniSquirrel){
        return false;
    }




    public String toString(){
        return "S";
    }
}
