package de.hsa.games.fatsquirrel.core;


public class BadBeast extends Movable{

    private static final int ENERGY = -150;

    public BadBeast(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep() {

    }

    public String toString(){
        return "B";
    }


}
