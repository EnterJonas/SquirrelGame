package de.hsa.games.fatsquirrel.core;


public class Wall extends Entity {

    private static final int ENERGY = -10;

    public Wall(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    public String toString(){
        return "#";
    }

}
