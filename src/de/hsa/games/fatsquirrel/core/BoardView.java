package de.hsa.games.fatsquirrel.core;


public interface BoardView {

    EntityTypes getEntityType(int y, int x);
    Entity[][] getWorld();

}
