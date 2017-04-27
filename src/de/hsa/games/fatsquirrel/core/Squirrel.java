package de.hsa.games.fatsquirrel.core;


public abstract class Squirrel extends Movable {

    private Entity nextFood;
    private int suspensionCounter = 0;
    protected boolean hasBeenSuspended = false;

    public Squirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }

    protected boolean isFoodAround(EntityContext context, int VISION) {
        nextFood = context.nearestPlayerEntity(this.getPosition());
        return nextFood.getPosition().getSteps(this.getPosition()) <= VISION;
    }

    protected void eat(XY position, EntityContext context) {
        Entity intersectingEntity = context.getEntitySet().getSet().getIntersectingObject(position, this);
        this.updateEnergy(intersectingEntity.getEnergy());
        context.killAndReplace(intersectingEntity);
    }

    protected void whatToDo(EntityContext context, XY moveDirection){
        EntityTypes intersectingEntity = context.getEntitySet().getSet().getIntersectingObject(moveDirection,null).getEntityType();
        if(intersectingEntity == EntityTypes.Wall){
            updateSuspensionCounter(3);
            this.hasBeenSuspended = true;
        }else if(intersectingEntity == EntityTypes.BadBeast){
            this.updatePosition(moveDirection);
        }else{
            this.updatePosition(moveDirection);
            eat(moveDirection, context);
        }
    }

    protected int getSuspensionCounter(){
        return suspensionCounter;
    }

    protected void updateSuspensionCounter(int deltaSuspension){
        this.suspensionCounter += deltaSuspension;
    }


}
