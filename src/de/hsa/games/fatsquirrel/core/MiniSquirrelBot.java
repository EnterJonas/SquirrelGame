package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.OutOfViewException;
import de.hsa.games.fatsquirrel.util.XY;

import java.util.Random;
import java.util.logging.Logger;

public class MiniSquirrelBot extends MiniSquirrel {

    Random random = new Random();
    private static final int VISION = 20;
    private MasterSquirrel parent;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    BotControllerFactory botControllerFactory;
    BotController miniBotController;


    public MiniSquirrelBot(EntityType entityType, int energy, XY position, MasterSquirrel parent) {
        super(entityType, energy, position, parent);
        this.parent = parent;
        this.miniBotController = botControllerFactory.createMiniBotController();
    }


    @Override
    public void nextStep(EntityContext context) {
        miniBotController.nextStep(new ControllerContextImpl(context, this));
    }


    class ControllerContextImpl implements ControllerContext {

        final int MAX_VISION = 10;

        private EntityContext context;
        private MiniSquirrel miniSquirrel;

        public ControllerContextImpl(EntityContext context, MiniSquirrel miniSquirrel) {
            this.context = context;
            this.miniSquirrel = miniSquirrel;
        }


        @Override
        public XY getViewLowerLeft() {
            return miniSquirrel.getPosition().plus(MAX_VISION, -MAX_VISION);
        }

        @Override
        public XY getViewUpperRight() {
            return miniSquirrel.getPosition().plus(-MAX_VISION, MAX_VISION);
        }

        @Override
        public XY locate() {
            return miniSquirrel.getPosition();
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            try {
                if (context.getEntityType(xy) != null) {
                    if (outOfVision(xy)) {
                        throw new OutOfViewException("Entity you're looking for is not in vision!");
                    }
                    return context.getEntityType(xy);
                }
            } catch (OutOfViewException e) {
                LOGGER.info(e.getMessage());
            }
            return null;
        }

        private boolean outOfVision(XY position) {
            //if inside lower_left
            if (position.getX() >= getViewLowerLeft().getX() && position.getY() <= getViewLowerLeft().getY()) {
                //if inside upper_right
                if (position.getX() <= getViewUpperRight().getX() && position.getY() >= getViewUpperRight().getY()) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean isMine(XY xy) {
            return false;
        }

        @Override
        public void move(XY direction) {
            context.tryMove(miniSquirrel, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            //minis do not spawn minis
        }

        @Override
        public int getEnergy() {
            return miniSquirrel.getEnergy();
        }

        @Override
        public XY directionOfMaster() {
            int x, y;
            x = y = 0;
            XY vector = miniSquirrel.getPosition().minus(miniSquirrel.getParent().getPosition());
            if (vector.getY() > 0) {
                y = 1;
            } else if (vector.getY() < 0) {
                y = -1;
            }
            if (vector.getX() > 0) {
                x = 1;
            } else if (vector.getX() < 0) {
                x = -1;
            }
            return new XY(y, x);
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }

        @Override
        public void implode() {
            int impactRadius = random.nextInt(MAX_VISION + 1 - 2) + 2;
            int impactArea = (int) Math.round(Math.pow(impactRadius, 2) * Math.PI);

            //decides what kind of entities are affected by implosion
            EntityType[] entitiesToLookFor = {
                    EntityType.MiniSquirrel,
                    EntityType.MasterSquirrelBot,
                    EntityType.HandOperatedMasterSquirrel,
                    EntityType.BadPlant,
                    EntityType.BadBeast,
                    EntityType.GoodBeast,
                    EntityType.GoodPlant
            };

            //stores found entities in array
            Entity[] entities = context.nearestEntity(miniSquirrel.getPosition(), entitiesToLookFor);

            int totalImplosionEnergy = 0;
            //check distance, whether in range or not
            for (Entity currentSelected : entities) {
                double distance = miniSquirrel.getPosition().distanceFrom(currentSelected.getPosition());
                //if current Entity in range
                if (distance <= impactRadius) {
                    //if current Entity that's being looked at ain't part of the family
                    if (!(miniSquirrel.getParent().equals(currentSelected)) || ((MiniSquirrel) currentSelected).getParent().equals(miniSquirrel.getParent())) {
                        int energyLoss = (int) (200 * (miniSquirrel.getEnergy() / impactArea) * (1 - distance / impactRadius));
                        //make energyLoss negative if entity is off good nature
                        if (!(currentSelected.getEntityType() == EntityType.BadPlant || currentSelected.getEntityType() == EntityType.BadBeast)) {
                            energyLoss *= -1;
                        }
                        //decide whether there is enough energy left in selected entity
                        if (Math.abs(currentSelected.getEnergy()) >= Math.abs(energyLoss)) {
                            currentSelected.updateEnergy(energyLoss);
                            totalImplosionEnergy += energyLoss;
                        } else {
                            totalImplosionEnergy += currentSelected.getEnergy();
                            if (!(currentSelected instanceof MasterSquirrel)) {
                                context.killAndReplace(currentSelected);
                            }
                            //if current selected entity is masterSquirrel, do not kill and replace
                            currentSelected.updateEnergy(currentSelected.getEnergy());
                        }
                    }
                }
            }
            miniSquirrel.getParent().updateEnergy(totalImplosionEnergy);
            context.kill(miniSquirrel);
        }
    }

    public String toString() {
        return "Entity: " + this.getUID() + " Mini_Bot";
    }


}
