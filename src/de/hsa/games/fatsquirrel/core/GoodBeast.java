package de.hsa.games.fatsquirrel.core;


public class GoodBeast extends Movable {

    private static final int ENERGY = 200;

    public GoodBeast(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context) {

    }

    public String toString(){
        return "G";
    }

}
