package de.hsa.games.fatsquirrel.core;


public abstract class MasterSquirrel extends Squirrel {

    protected int suspensionCounter = 0;

    public MasterSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }


    protected void eat(XY position, EntityContext context){
        Entity intersectingEntity = context.getEntitySet().getSet().getIntersectingObject(position,this);
        this.updateEnergy(intersectingEntity.getEnergy());
        context.killAndReplace(intersectingEntity);
    }

    protected void whatToDo(EntityContext context, XY moveDirection){
        XY temp = this.getPosition().setNewPosition(this.getPosition(),moveDirection);
        EntityTypes intersectingEntity = context.getEntitySet().getSet().getIntersectingObject(temp,null).getEntityType();
        if(intersectingEntity == EntityTypes.Wall){
            suspensionCounter = 3;
        }else if(intersectingEntity == EntityTypes.BadBeast){

        }else{
            this.updatePosition(temp);
            eat(temp, context);
        }
    }


    public String toString(){
        return "S";
    }
}
