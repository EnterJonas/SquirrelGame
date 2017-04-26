package de.hsa.games.fatsquirrel.core;


public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private static final int ENERGY = 1000;


    public HandOperatedMasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);

    }

    @Override
    public void nextStep(EntityContext context, XY moveDirection) {
        this.updatePosition(this.getPosition().setNewPosition(this.getPosition(), moveDirection));
    }


}
