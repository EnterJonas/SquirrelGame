package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.interfaces.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

public class BotSquirrel extends MasterSquirrel {

    private static final int ENERGY = 1000;

    public BotSquirrel(EntityTypes entityType, int energy, XY position) {
        super(entityType, energy + ENERGY, position);
    }

    @Override
    public void nextStep(EntityContext context, XY moveDirection) {

    }

}
