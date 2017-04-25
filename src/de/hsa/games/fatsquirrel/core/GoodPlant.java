package de.hsa.games.fatsquirrel.core;


public class GoodPlant extends Entity {

    private static final int ENERGY = 100;

    public GoodPlant(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    public String toString(){
        return "g";
    }

}
