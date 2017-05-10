package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;

public class MiniSquirrel extends Squirrel {

    private static final int VISION = 10;
    private int timer = 0;
    private MasterSquirrel father;


    public MiniSquirrel(EntityTypes entityType, int energy, XY position, MasterSquirrel father) {
        super(entityType, energy, position);
        this.father = father;
    }

    @Override
    public void nextStep(EntityContext context) {
       if(!isStunned()){
           XY estimateDestination = this.getPosition().getNewPosition();
           context.tryMove(this, estimateDestination);
       }else{
           setSuspensionCounter(-1);
       }
       this.updateEnergy(-1);

    }

    public MasterSquirrel getParent(){
        return this.father;
    }

    public void updateTimer(){
        this.timer++;
    }

    public int getTimer(){
        return this.timer;
    }

    public int getVision(){
        return VISION;
    }

    public String toString() {
        return "M";
    }


}
