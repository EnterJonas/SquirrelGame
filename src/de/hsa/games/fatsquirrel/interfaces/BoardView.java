package de.hsa.games.fatsquirrel.interfaces;

import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.entities.EntityTypes;


public interface BoardView {

    EntityTypes getEntityType(int y, int x);
    Entity[][] getWorld();

}
