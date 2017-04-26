package de.hsa.games.fatsquirrel.core;


public interface EntityContext {

    Squirrel nearestPlayerEntity(XY positionOfEntityLookingForPlayer);
    void tryMove(EntityTypes entityType, XY moveDirection);
    EntitySet getEntitySet();




}
