package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private static final int ENERGY = 1000;
    private XY input;

    public HandOperatedMasterSquirrel(EntityType entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context) {
        if(getInput() != null){
            if(!isStunned()){
                XY nextPosition = new XYsupport().setNewPosition(this.getPosition(), getInput());
                setInput(null);
                context.tryMove(this,nextPosition);
            }else{
                setSuspensionCounter(-1);
            }
        }
    }

    public void setInput(XY input){
        this.input = input;
    }

    public XY getInput(){
        return this.input;
    }

    public String toString(){
        return "GuidedMasterSquirrel";
    }


}
