package de.hsa.games.fatsquirrel.core.entities.nonmovable;


import de.hsa.games.fatsquirrel.core.entities.EntityTypes;
import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.util.XY;

public class Wall extends Entity {

    private static final int ENERGY = -10;

    public Wall(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    public String toString(){
        return "#";
    }

}
