package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.EntitySet;
import de.hsa.games.fatsquirrel.core.BadBeast;
import de.hsa.games.fatsquirrel.core.GoodBeast;
import de.hsa.games.fatsquirrel.core.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.MiniSquirrel;
import de.hsa.games.fatsquirrel.core.Squirrel;
import de.hsa.games.fatsquirrel.util.XY;

public interface EntityContext {

    void tryMove(GoodBeast goodBeast, XY moveDirection);
    void tryMove(BadBeast badBeast, XY moveDirection);
    void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
    void tryMove(MasterSquirrel masterSquirrel, XY moveDirection);

    void killAndReplace(Entity entityToKill);
    void kill(Entity entityToKill);
    void giveBirth(XY position, int parentID);
}
