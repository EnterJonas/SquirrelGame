package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private static final int ENERGY = 1000;
    private XY input;

    public HandOperatedMasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context) {
        if(getInput() != null){
            if(!isStunned()){
                XY nextPosition = this.getPosition().setNewPosition(getInput());
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


}
