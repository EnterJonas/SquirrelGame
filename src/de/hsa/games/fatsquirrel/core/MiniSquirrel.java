package de.hsa.games.fatsquirrel.core;


public class MiniSquirrel extends Squirrel {

    private static final int VISION = 10;


    public MiniSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }

    @Override
    public void nextStep(EntityContext context, XY moveDirection) {
        updateEnergy(-1);
        XY temp;

        //has been suspended boolean is used to make the miniSquirrel "unstuck" from walls..
        if(isFoodAround(context, VISION) && !hasBeenSuspended){
            temp = this.getPosition().setNewPosition(this.getPosition(), this.getPosition().createMovementVector(getNextSquirrel().getPosition().createVector(this.getPosition())));
            whatToDo(context, temp);
        }else if (isFoodAround(context,VISION) && hasBeenSuspended){
            runAround(context);
            hasBeenSuspended = false;
        }
        else{
            runAround(context);
        }
    }




}
