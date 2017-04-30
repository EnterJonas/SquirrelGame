package de.hsa.games.fatsquirrel.interfaces;

import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntityTypes;


public interface BoardView {

    EntityTypes getEntityType(int y, int x);
    Entity[][] getWorld();

}
