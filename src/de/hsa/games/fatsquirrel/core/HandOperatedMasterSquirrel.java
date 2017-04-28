package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.interfaces.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private static final int ENERGY = 1000;


    public HandOperatedMasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);

    }

    @Override
    public void nextStep(EntityContext context, XY moveDirection) {
        if (!context.getEntitySet().getSet().isIntersecting(this.getPosition().setNewPosition(this.getPosition(), moveDirection)) && getSuspensionCounter() <= 0) {
            this.updatePosition(this.getPosition().setNewPosition(this.getPosition(), moveDirection));
        }else if (!context.getEntitySet().getSet().isIntersecting(this.getPosition().setNewPosition(this.getPosition(), moveDirection)) && getSuspensionCounter() > 0){
            updateSuspensionCounter(-1);
        }else{
            XY temp = this.getPosition().setNewPosition(this.getPosition(), moveDirection);
            whatToDo(context,temp);
        }
        if(this.getEnergy() >= 1200){
            giveBirthToMiniSquirrel(100,context);
            this.updateEnergy(-200);
        }

    }


}
