package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.interfaces.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

public abstract class Movable extends Entity {

    private Squirrel nextSquirrel;

    public Movable(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }

    public abstract void nextStep(EntityContext context, XY moveDirection);

    public void runAround(EntityContext context){
        XY temp = this.getPosition().getNewPosition(this.getPosition());
        if(!(context.getEntitySet().getSet().isIntersecting(temp))){
            updatePosition(temp);
        }else runAround(context);
    }

    public boolean isSquirrelAround(EntityContext context, int VISION){
        nextSquirrel = context.nearestPlayerEntity(this.getPosition());
        return nextSquirrel.getPosition().getSteps(this.getPosition()) <= VISION;
    }

    public Squirrel getNextSquirrel(){
        return nextSquirrel;
    }

}
