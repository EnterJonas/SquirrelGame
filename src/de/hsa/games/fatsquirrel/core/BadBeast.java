package de.hsa.games.fatsquirrel.core;


public class BadBeast extends Movable {

    private static final int ENERGY = -150;
    private static final int VISION = 6;
    private int bites = 7;
    private EntityContext context;


    public BadBeast(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context) {
        if (isSquirrelAround(context, VISION)) {
            follow(context);
        } else {
            runAround(context);
        }
    }

    private void follow(EntityContext context) {
        this.context = context;
        XY temp = this.getPosition().setNewPosition(this.getPosition(), this.getPosition().createMovementVector(getNextSquirrel().getPosition().createVector(this.getPosition())));
        Entity intersectingEntity = context.getEntitySet().getSet().getIntersectingObject(temp,this);
        //if nothing is intersecting move to temp position
        if (!(context.getEntitySet().getSet().isIntersecting(temp))) {
            updatePosition(temp);
        }
        //if a Squirrel is at position
        else if(intersectingEntity instanceof Squirrel){
            updatePosition(intersectingEntity.getPosition());
            bite();
        //if something else is at position
        }else runAround(context);


    }

    private void bite() {
        bites--;
        if(bites == 0){
            context.killAndReplace(this);
        }
    }

    public String toString() {
        return "B";
    }


}
