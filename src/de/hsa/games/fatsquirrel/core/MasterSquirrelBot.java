package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.util.XY;

public class MasterSquirrelBot extends MasterSquirrel {

    private static final int ENERGY = 1000;
    private BotControllerFactory botControllerFactory;
    private BotController masterBotController;


    public MasterSquirrelBot(EntityTypes entityType, int energy, XY position, String typeOfTheBot) {
        super(entityType, energy + ENERGY, position);
        switch (typeOfTheBot) {
            case ("idk"):

                this.botControllerFactory = new BotControllerFactory() {
                    @Override
                    public BotController createMasterBotController() {
                        return null;
                    }

                    @Override
                    public BotController createMiniBotController() {
                        return null;
                    }
                };
                break;
        }
        this.masterBotController = botControllerFactory.createMasterBotController();

    }

    @Override
    public void nextStep(EntityContext context) {
        masterBotController.nextStep(new ControllerContextImpl(context, this));
    }


    class ControllerContextImpl implements ControllerContext {

        private EntityContext context;
        private MasterSquirrel masterSquirrel;

        public ControllerContextImpl(EntityContext context, MasterSquirrel masterSquirrel) {
            this.context = context;
            this.masterSquirrel = masterSquirrel;
        }

        @Override
        public XY getViewLowerLeft() {
            return masterSquirrel.getPosition().add(1, -1);
        }

        @Override
        public XY getViewUpperRight() {
            return masterSquirrel.getPosition().add(-1, 1);
        }

        @Override
        public EntityTypes getEntityAt(XY xy) {
            return context.getEntityType(xy);
        }

        @Override
        public void move(XY direction) {
            context.tryMove(masterSquirrel, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            //TODO not sure what to do cause impl already in gameImpl
        }

        @Override
        public int getEnergy() {
            return masterSquirrel.getEnergy();
        }

    }


}
