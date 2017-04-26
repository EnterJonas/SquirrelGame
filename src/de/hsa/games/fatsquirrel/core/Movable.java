package de.hsa.games.fatsquirrel.core;

public abstract class Movable extends Entity {

    public Movable(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy, position);
    }

    public abstract void nextStep();


}
