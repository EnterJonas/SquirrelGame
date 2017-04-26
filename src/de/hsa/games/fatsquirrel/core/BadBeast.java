package de.hsa.games.fatsquirrel.core;


public class BadBeast extends Movable{

    private static final int ENERGY = -150;
    private static final int VISION = 6;


    public BadBeast(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context) {
        if(isSquirrelAround(context, VISION)){
            follow(context);
        }else{
            runAround(context);
        }
    }

    private void follow(EntityContext context){
        XY temp = this.getPosition().setNewPosition(this.getPosition(),this.getPosition().createMovementVector(getNextSquirrel().getPosition().createVector(this.getPosition())));
        if(!(context.getEntitySet().getSet().isIntersecting(temp))){
            updatePosition(temp);
        }else follow(context);
    }

    public String toString(){
        return "B";
    }


}
