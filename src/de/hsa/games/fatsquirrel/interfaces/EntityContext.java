package de.hsa.games.fatsquirrel.interfaces;


import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.util.XY;

public interface EntityContext {

    Squirrel nearestPlayerEntity(XY positionOfEntityLookingForPlayer);
    Entity nearestFood(XY positionOfEntityLookingForFood);
    void tryMove(EntityTypes entityType, XY moveDirection);
    EntitySet getEntitySet();
    void killAndReplace(Entity entityToKill);
    void kill(Entity entityToKill);
    void giveBirth(MiniSquirrel miniSquirrel);




}
