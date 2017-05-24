package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;


public interface BoardView {

    EntityType getEntityType(int y, int x);
    XY getSize();

}
