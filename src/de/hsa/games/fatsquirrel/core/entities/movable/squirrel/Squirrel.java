package de.hsa.games.fatsquirrel.core.entities.movable.squirrel;


import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.entities.movable.Movable;
import de.hsa.games.fatsquirrel.core.entities.EntityTypes;
import de.hsa.games.fatsquirrel.interfaces.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

public abstract class Squirrel extends Movable {

    private Entity nextFood;
    private int suspensionCounter = 0;
    protected boolean hasBeenSuspended = false;

    public Squirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }

    protected boolean isFoodAround(EntityContext context, int VISION) {
        nextFood = context.nearestFood(this.getPosition());
        return nextFood.getPosition().getSteps(this.getPosition()) <= VISION;
    }

    protected Entity getNextFood(){
        return this.nextFood;
    }

    protected void eat(XY position, EntityContext context) {
        Entity intersectingEntity = context.getEntitySet().getSet().getIntersectingObject(position, this);
        this.updateEnergy(intersectingEntity.getEnergy());
        context.killAndReplace(intersectingEntity);
    }

    protected void whatToDo(EntityContext context, XY moveDirection){
        if(context.getEntitySet().getSet().isIntersecting(moveDirection)) {
            EntityTypes intersectingEntity = context.getEntitySet().getSet().getIntersectingObject(moveDirection, null).getEntityType();
            if (intersectingEntity == EntityTypes.Wall) {
                updateSuspensionCounter(3);
                this.hasBeenSuspended = true;
            } else if (intersectingEntity == EntityTypes.BadBeast) {
                this.updatePosition(moveDirection);
            } else {
                this.updatePosition(moveDirection);
                eat(moveDirection, context);
            }
        }
    }

    protected int getSuspensionCounter(){
        return suspensionCounter;
    }

    protected void updateSuspensionCounter(int deltaSuspension){
        this.suspensionCounter += deltaSuspension;
    }

    protected boolean hasBeenSuspended(){
        return hasBeenSuspended;
    }

    protected void updateSuspentionState(){
        this.hasBeenSuspended = false;
    }


}
