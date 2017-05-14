package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.util.XY;

public class MiniSquirrelBot extends MiniSquirrel {


    private static final int VISION = 20;
    private MasterSquirrel parent;

    BotControllerFactory botControllerFactory;
    BotController miniBotController;


    public MiniSquirrelBot(EntityTypes entityType, int energy, XY position, MasterSquirrel parent) {
        super(entityType, energy, position, parent);
        this.parent = parent;
        this.miniBotController = botControllerFactory.createMiniBotController();
    }




    class ControllerContextImpl implements ControllerContext {

        private EntityContext context;
        private MiniSquirrel miniSquirrel;

        public ControllerContextImpl(EntityContext context,
                                     MiniSquirrel miniSquirrel) {
            this.context = context;
            this.miniSquirrel = miniSquirrel;
        }

        @Override
        public XY getViewLowerLeft() {
            return miniSquirrel.getPosition().add(1, -1);
        }

        @Override
        public XY getViewUpperRight() {
            return miniSquirrel.getPosition().add(-1, 1);
        }

        @Override
        public EntityTypes getEntityAt(XY xy) {
            return context.getEntityType(xy);
        }

        @Override
        public void move(XY direction) {
            context.tryMove(miniSquirrel, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
        }

        @Override
        public int getEnergy() {
            return miniSquirrel.getEnergy();
        }

    }


}
