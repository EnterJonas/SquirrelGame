package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class MiniSquirrel extends Squirrel {

    private static final int VISION = 10;
    private MasterSquirrel parent;


    public MiniSquirrel(EntityType entityType, int energy, XY position, MasterSquirrel parent) {
        super(entityType, energy, position);
        this.parent = parent;
    }



    @Override
    public void nextStep(EntityContext context) {
       if(!isStunned()){
           XY estimateDestination = new XYsupport().getNewPosition(this.getPosition());
           context.tryMove(this, estimateDestination);
       }else{
           setSuspensionCounter(-1);
       }
       this.updateEnergy(-1);

    }

    public MasterSquirrel getParent(){
        return this.parent;
    }

    public int getVision(){
        return VISION;
    }

    public String toString() {
        return "MINI_SQUIRREL";
    }


}
