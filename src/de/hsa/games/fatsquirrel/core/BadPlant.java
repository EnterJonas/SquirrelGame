package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.core.EntityTypes;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.util.XY;

public class BadPlant extends Entity {

    private static final int ENERGY = -100;

    public BadPlant(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    public String toString(){
        return "b";
    }
}
