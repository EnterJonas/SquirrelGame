package de.hsa.games.fatsquirrel.core;


public class BadPlant extends Entity{

    private static final int ENERGY = -100;

    public BadPlant(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    public String toString(){
        return "b";
    }
}
