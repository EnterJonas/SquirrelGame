package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.interfaces.EntityContext;
import de.hsa.games.fatsquirrel.util.List;
import de.hsa.games.fatsquirrel.util.XY;

public abstract class MasterSquirrel extends Squirrel {

    private static int amountPlayerEntities;
    private int parentID = amountPlayerEntities;

    public MasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
        amountPlayerEntities++;
    }

    public boolean isParent(Entity entity){

        /**
         * needs implementation
         */
        return false;

    }

    public int getParentID(){
        return this.parentID;
    }




    public void createNewMiniSquirrel(EntityContext context){
        context.giveBirth(this.getPosition(), this.getParentID());
    }


    public String toString(){
        return "S";
    }
}
