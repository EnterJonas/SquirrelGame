package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;

public abstract class MasterSquirrel extends Squirrel {

    private static int amountPlayerEntities;
    private int parentID = amountPlayerEntities;

    public MasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
        amountPlayerEntities++;
    }

    public boolean isParent(MiniSquirrel miniSquirrel){
        return miniSquirrel.getParentID() == this.getParentID();
    }

    public int getParentID(){
        return this.parentID;
    }

    //TODO noch bearbeiten
//    public void createNewMiniSquirrel(EntityContext context){
//        context.giveBirth(this.getPosition(), 199, this.getParentID());
//    }

    public String toString(){
        return "S";
    }
}
