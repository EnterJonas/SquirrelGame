package de.hsa.games.fatsquirrel.core.entities.movable.squirrel;


import de.hsa.games.fatsquirrel.core.entities.EntityTypes;
import de.hsa.games.fatsquirrel.interfaces.EntityContext;
import de.hsa.games.fatsquirrel.util.List;
import de.hsa.games.fatsquirrel.util.XY;

public abstract class MasterSquirrel extends Squirrel {

    private List list = new List();

    public MasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }


//    public boolean isParent(){
//        int position = list.getSize();
//        while(list.getEntityAtPosition(position) != null){
//            if(list.getEntityAtPosition(position))
//            position--;
//        }
//    }

    protected void giveBirthToMiniSquirrel(int energyOfMiniSquirrel, EntityContext context){
        list.add(new MiniSquirrel(EntityTypes.MiniSquirrel, energyOfMiniSquirrel, this.getPosition()));
        context.giveBirth((MiniSquirrel) list.getEntityAtPosition(list.getSize()));
    }



    public String toString(){
        return "S";
    }
}
