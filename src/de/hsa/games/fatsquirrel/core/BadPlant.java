package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;

public class BadPlant extends Entity {

    private static final int ENERGY = -100;

    public BadPlant(EntityType entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    public String toString(){
        return "BadPlant";
    }
}
