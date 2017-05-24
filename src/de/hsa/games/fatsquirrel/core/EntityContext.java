package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;

public interface EntityContext {

    XY getSize();

    void tryMove(GoodBeast goodBeast, XY moveDirection);
    void tryMove(BadBeast badBeast, XY moveDirection);
    void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
    void tryMove(MasterSquirrel masterSquirrel, XY moveDirection);
    Entity[] nearestEntity(XY positionOfEntityLookingForPlayer, EntityType ... no);

    void killAndReplace(Entity entityToKill);
    void kill(Entity entityToKill);
    EntityType getEntityType(XY position);


}
