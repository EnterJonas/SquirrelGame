package de.hsa.games.fatsquirrel.core;


public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private static final int ENERGY = 1000;


    public HandOperatedMasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);

    }

    @Override
    public void nextStep(EntityContext context, XY moveDirection) {
        if (!context.getEntitySet().getSet().isIntersecting(this.getPosition().setNewPosition(this.getPosition(), moveDirection)) && suspensionCounter <= 0) {
            this.updatePosition(this.getPosition().setNewPosition(this.getPosition(), moveDirection));
        }else if (!context.getEntitySet().getSet().isIntersecting(this.getPosition().setNewPosition(this.getPosition(), moveDirection)) && suspensionCounter > 0){
            suspensionCounter--;
        }else{
            whatToDo(context,moveDirection);
        }
    }


}
