package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.core.EntityTypes;
import de.hsa.games.fatsquirrel.util.XY;

public interface ControllerContext {

    XY getViewLowerLeft();

    XY getViewUpperRight();

    EntityTypes getEntityAt(XY position);

    void move(XY direction);

    void spawnMiniBot(XY direction, int energy);

    int getEnergy();

}
