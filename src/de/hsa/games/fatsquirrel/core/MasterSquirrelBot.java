package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.SpawnException;
import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

import java.util.logging.Logger;

public class MasterSquirrelBot extends MasterSquirrel {

    private static final int ENERGY = 1000;
    private static final int SPAWN_ENERGY = 100;
    private BotControllerFactory botControllerFactory;
    private BotController masterBotController;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public MasterSquirrelBot(EntityType entityType, int energy, XY position, String typeOfTheBot) {
        super(entityType, energy + ENERGY, position);
        switch (typeOfTheBot) {
            case ("idk"):

                this.botControllerFactory = new BotControllerFactory() {
                    @Override
                    public BotController createMasterBotController() {
                        return new BotController() {
                            @Override
                            public void nextStep(ControllerContext view) {
                                if(!isStunned()){
                                    XY direction = new XYsupport().getNewPosition(getPosition());
                                    view.move(direction);
                                }else{
                                    setSuspensionCounter(-1);
                                }
                            }
                        };
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
            return masterSquirrel.getPosition().plus(15, -15);
        }

        @Override
        public XY getViewUpperRight() {
            return masterSquirrel.getPosition().plus(-15, 15);
        }

        @Override
        public XY locate() {
            return null;
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return context.getEntityType(xy);
        }

        @Override
        public boolean isMine(XY xy) {
            return false;
        }

        @Override
        public void move(XY direction) {
            context.tryMove(masterSquirrel, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            try {
                if(energy >= masterSquirrel.getEnergy()){
                    throw new SpawnException("Nicht genug Energie oder Richtung nicht verfügbar!");
                }
            } catch (SpawnException e) {
                LOGGER.warning(e.getMessage());
            }
        }

        @Override
        public void implode() {
            //masterSquirrels cannot implode...
        }

        @Override
        public int getEnergy() {
            return masterSquirrel.getEnergy();
        }

        @Override
        public XY directionOfMaster() {
            return null;
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }

    }

    @Override
    public String toString(){
        return "Bot";
    }


}
