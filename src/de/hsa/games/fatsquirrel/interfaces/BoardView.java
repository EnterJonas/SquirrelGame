package de.hsa.games.fatsquirrel.interfaces;


public interface BoardView {

    EntityTypes getEntityType(int y, int x);
    Entity[][] getWorld();

}
