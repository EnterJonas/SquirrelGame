package de.hsa.games.fatsquirrel.core;


public interface EntityContext {

    Squirrel nearestPlayerEntity(XY positionOfEntityLookingForPlayer);
    Entity nearestFood(XY positionOfEntityLookingForFood);
    void tryMove(EntityTypes entityType, XY moveDirection);
    EntitySet getEntitySet();
    void killAndReplace(Entity entityToKill);
    void kill(Entity entityToKill);




}
