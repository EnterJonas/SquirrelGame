package de.hsa.games.fatsquirrel.core;


public class GoodBeast extends Movable {

    private static final int ENERGY = 200;
    private static final int VISION = 6;
    private Squirrel nextSquirrel;
    private EntityContext context;


    public GoodBeast(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context) {
        this.context = context;
        if(isSquirrelAround(context, VISION)){
            runAway();
        }else{
            runAround(context);
        }
    }

    private void runAway(){
        XY temp = this.getPosition().setNewPosition(this.getPosition(),this.getPosition().createMovementVector(getNextSquirrel().getPosition().createVector(this.getPosition())));
        XY temp2 = new XY(temp.getY()*-1, temp.getX()*-1);
        if(!(context.getEntitySet().getSet().isIntersecting(temp))){
            updatePosition(temp2);
        }else runAway();
    }

    public String toString(){
        return "G";
    }

}
