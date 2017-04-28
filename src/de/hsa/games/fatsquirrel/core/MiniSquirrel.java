package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.interfaces.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

public class MiniSquirrel extends Squirrel {

    private static final int VISION = 10;


    public MiniSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }

    @Override
    public void nextStep(EntityContext context, XY moveDirection) {
        updateEnergy(-1);
        if(isFoodAround(context, VISION)){
            getFood(context);
        }else {
            runAround(context);
        }
    }

    private void getFood(EntityContext context) {
        XY temp = this.getPosition().setNewPosition(this.getPosition(), this.getPosition().createMovementVector(getNextFood().getPosition().createVector(this.getPosition())));
        Entity intersectingEntity = context.getEntitySet().getSet().getIntersectingObject(temp, this);
        //if nothing is intersecting move to temp position
        if (!(context.getEntitySet().getSet().isIntersecting(temp))) {
            updatePosition(temp);
        }
        //if food is at Position
        else if (intersectingEntity.getEntityType() == EntityTypes.GoodBeast || intersectingEntity.getEntityType() == EntityTypes.GoodPlant) {
            context.killAndReplace(intersectingEntity);
            updatePosition(intersectingEntity.getPosition());
            updateEnergy(intersectingEntity.getEnergy());
            //if something else is at position
        } else runAround(context);
    }

    public String toString(){
        return "M";
    }



}
