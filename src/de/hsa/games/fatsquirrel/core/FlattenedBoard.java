package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

import java.util.logging.Logger;

public class FlattenedBoard implements EntityContext, BoardView {

//    private class Board {
//
//        private BoardConfig boardConfig;
//        private Entity[][] emptyWorld;
//        private Entity[][] initWorld;
//        private EntitySet entitySet;
//
//        //Board constructor
//        private Board() {
//            this.boardConfig = new BoardConfig();
//            this.emptyWorld = new Entity[boardConfig.getPitchHeight()][boardConfig.getPitchWidth()];
//            this.initWorld = new Entity[boardConfig.getPitchHeight()][boardConfig.getPitchWidth()];
//            this.entitySet = new EntitySet();
//            createNewWorld();
//            createInitWorld();
//        }
//
//        //creates empty World
//        private void createNewWorld() {
//            for (int y = 0; y < emptyWorld.length; y++) {
//                for (int x = 0; x < emptyWorld[y].length; x++) {
//                    if (y == 0 || x == 0 || y == (emptyWorld.length - 1) || x == (emptyWorld[y].length - 1)) {
//                        entitySet.addNewEntity(EntityType.Wall, 0, new XY(y, x), 0);
//                    }
//                }
//            }
//            String[][] listingCopy = boardConfig.getEntityListing().clone();
//            for (int w = 0; w < Integer.parseInt((listingCopy[0][1])); w++) {
//                createNewEntity(EntityType.valueOf(listingCopy[0][0]));
//            }
//
//            createWalls(emptyWorld);
//        }
//
//        //creates Wall entities for empty World
//        private void createWalls(Entity[][] emptyWorld) {
//            int position = entitySet.getSet().getSize();
//            while (position > 0) {
//                Entity current = entitySet.getSet().getEntityAtPosition(position--);
//                XY cP = current.getPosition(); //cP: currentPosition
//                emptyWorld[cP.getY()][cP.getX()] = current;
//            }
//        }
//
//        //creates populated World
//        private void createInitWorld() {
//            initWorld = cloneWorld(emptyWorld);
//            String[][] listingCopy = boardConfig.getEntityListing().clone();
//            for (int z = 1; z < listingCopy.length; z++) {
//                for (int w = 0; w < Integer.parseInt(listingCopy[z][1]); w++) {
//                    createNewEntity(EntityType.valueOf(listingCopy[z][0]));
//                }
//            }
//            populateWorld(initWorld);
//        }
//
//        //creates new Entity, if position not already in use
//        private void createNewEntity(EntityType entityType) {
//            Random random = new Random();
//            for (int i = 0; i < 1; i++) {
//                int randomY = random.nextInt((initWorld.length - 2)) + 1;
//                int randomX = random.nextInt((initWorld[0].length - 2)) + 1;
//                XY randomPosition = new XY(randomY, randomX);
//                if (!entitySet.getSet().isIntersecting(randomPosition)) {
//                    entitySet.addNewEntity(entityType, 0, new XY(randomY, randomX), 0);
//                } else {
//                    i--;
//                }
//            }
//        }
//
//        //writes world array
//        private void populateWorld(Entity[][] world) {
//            int position = entitySet.getSet().getSize();
//            Entity current = entitySet.getSet().getEntityAtPosition(position);
//            while (current.getEntityType() != EntityType.Wall) {
//                current = entitySet.getSet().getEntityAtPosition(position);
//                XY cP = current.getPosition(); //cP: currentPosition
//                world[cP.getY()][cP.getX()] = current;
//                position--;
//            }
//        }
//
//        //clones Worlds (2D-Entity-Arrays)
//        private Entity[][] cloneWorld(Entity[][] arrayToClone) {
//            Entity[][] clonedArray = new Entity[arrayToClone.length][arrayToClone[0].length];
//            for (int y = 0; y < emptyWorld.length; y++) {
//                for (int x = 0; x < emptyWorld[y].length; x++) {
//                    clonedArray[y][x] = arrayToClone[y][x];
//                }
//            }
//            return clonedArray;
//        }
//
//        //prints the world (for testing purpose only)
////        private void printWorld() {
////            for (int x = 0; x < initWorld.length; x++) {
////                for (int y = 0; y < initWorld[x].length; y++) {
////                    if (initWorld[x][y] != null)
////                        System.out.print(initWorld[x][y]);
////                    else System.out.print(" ");
////                }
////                System.out.print('\n');
////            }
////        }
//
//        //places entities at their current position in world
//        private void updateWorld() {
//            initWorld = cloneWorld(emptyWorld);
//            populateWorld(initWorld);
//        }
//
//        //updates the world before returning it
//        private Entity[][] flatten() {
//            updateWorld();
//            return initWorld;
//        }
//
//        //returns EntitySet
//        private EntitySet getEntitySet() {
//            return this.entitySet;
//        }
//    }

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Board board;

    private Entity[][] flattenBoard;

    //constructor FlattenedBoard
    public FlattenedBoard(Board board) {
        this.board = board;
        this.flattenBoard = board.flatten();
    }


    public Entity[] nearestEntity(XY positionOfEntityLookingForEntities, EntityType... no) {
        Entity[] entities = null;
        try {
            entities = board.getEntitySet().collectRace(no);
        } catch (NullPointerException e) {
            LOGGER.warning("Flattenboard.nearestEntity() couldn't find any Entities!");
        }
        Entity temp;
        if (entities != null) {
            for (int i = 1; i < entities.length; i++) {
                for (int j = i; j > 0; j--) {
                    double distance1 = positionOfEntityLookingForEntities.distanceFrom(entities[j].getPosition());
                    double distance2 = positionOfEntityLookingForEntities.distanceFrom(entities[j - 1].getPosition());
                    if (distance1 < distance2) {
                        temp = entities[j];
                        entities[j] = entities[j - 1];
                        entities[j - 1] = temp;
                    }
                }
            }
        }
        return entities;
    }


    //returns nearest squirrel
//    public Entity[] nearestEntity(XY positionOfEntityLookingForPlayer, EntityType ... no) {
//        if (board.getEntitySet().collectRace(no) != null) {
//            Entity[] entities = board.getEntitySet().collectRace(no);
//            Entity temp;
//            for (int i = 1; i < entities.length; i++) {
//                for (int j = i; j > 0; j--) {
//                    int squirrelVector1 = entities[j].getPosition().getSteps(positionOfEntityLookingForPlayer);
//                    int squirrelVector2 = entities[j - 1].getPosition().getSteps(positionOfEntityLookingForPlayer);
//                    if (!(squirrelVector1 == 0 && squirrelVector2 == 0)) {
//                        if (squirrelVector1 < squirrelVector2) {
//                            temp = entities[j];
//                            entities[j] = entities[j - 1];
//                            entities[j - 1] = temp;
//                        }
//                    }
//                }
//            }
//            return entities;
//        }
//        return null;
//    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        Entity nearestSquirrel = null;
        //position of nextSquirrel
        try {
            nearestSquirrel = nearestEntity(badBeast.getPosition(), EntityType.MASTER_SQUIRREL, EntityType.MINI_SQUIRREL)[0];
        } catch (NullPointerException e) {
            LOGGER.finest(badBeast.toString() + " cannot execute move cause of intersecting object!");
        }

        if (nearestSquirrel != null) {
            XY nextSquirrel = nearestEntity(badBeast.getPosition(), EntityType.MASTER_SQUIRREL, EntityType.MINI_SQUIRREL)[0].getPosition();
            //if nextSquirrel is in range of bad-beast's vision
            if (badBeast.getPosition().distanceFrom(nextSquirrel) <= badBeast.getVision()) {
                //update moveDirection to attack the squirrel
                moveDirection = new XYsupport().setNewPosition(badBeast.getPosition(), new XYsupport().createMovementVector(new XYsupport().createVector(nextSquirrel, badBeast.getPosition())));
                LOGGER.info(badBeast + " hat Faehrte aufgenommen und verfolgt " + nearestSquirrel);
            }
        }

        //get intersecting Entity
        Entity intersectingEntity = flattenBoard[moveDirection.getY()][moveDirection.getX()];

        //if there actually is an intersecting entity
        if (intersectingEntity != null) {
            EntityType type = intersectingEntity.getEntityType();
            if (type == EntityType.MINI_SQUIRREL || type == EntityType.MASTER_SQUIRREL) {
                intersectingEntity.updateEnergy(badBeast.getEnergy());
                badBeast.setAmountBites(-1);
                LOGGER.info(badBeast + " beisst Squirrel");
                LOGGER.info("Uebrige Bisse: " + badBeast.getRemainingBites());
                if (badBeast.getRemainingBites() == 0) {
                    killAndReplace(badBeast);
                    LOGGER.info(badBeast + " ist gestorben");
                }
            }
        } else {
            badBeast.updatePosition(moveDirection);
            LOGGER.finest(badBeast + " bewegt sich nach " + moveDirection);
        }
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {
        Entity nearestSquirrel = null;
        //position of nextSquirrel
        try {
            nearestSquirrel = nearestEntity(goodBeast.getPosition(), EntityType.MASTER_SQUIRREL, EntityType.MINI_SQUIRREL)[0];
        } catch (NullPointerException e) {
            LOGGER.warning("Flattenboard.tryMove( " + goodBeast + " ) was not able to find any Squirrels in Game!");
        }

        if (nearestSquirrel != null) {
            //if squirrel is in vision
            if (goodBeast.getPosition().distanceFrom(nearestSquirrel.getPosition()) <= goodBeast.getVision()) {
                //create negative movement vector in order to run away
                XY movementVector = new XYsupport().createMovementVector(new XYsupport().createVector(nearestSquirrel.getPosition(), goodBeast.getPosition()));
                XY negativeMovementVector = new XY(movementVector.getY() * -1, movementVector.getX() * -1);
                moveDirection = new XYsupport().setNewPosition(goodBeast.getPosition(), negativeMovementVector);
                LOGGER.info(goodBeast + " ist auf der Flucht vor " + nearestSquirrel);
            }
        }

        //if nothing is intersecting move there
        if (getEntityType(moveDirection) == null) {
            goodBeast.updatePosition(moveDirection);
            LOGGER.finest(goodBeast + " bewegt sich nach " + moveDirection);
        }
    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
        Entity nextFood, nextSquirrel;
        nextFood = nextSquirrel = null;

        try {
            nextFood = nearestEntity(miniSquirrel.getPosition(), EntityType.GOOD_BEAST, EntityType.GOOD_PLANT)[0];
            nextSquirrel = nearestEntity(miniSquirrel.getPosition(), EntityType.MASTER_SQUIRREL)[0];
        } catch (NullPointerException e) {
            LOGGER.warning("Flattenboard.tryMove( " + miniSquirrel + " ) was not able to find either a squirrel, food or both!");
        }

        if (miniSquirrel.getPosition().distanceFrom(nextFood != null ? nextFood.getPosition() : null) < miniSquirrel.getVision()) {
            moveDirection = new XYsupport().setNewPosition(miniSquirrel.getPosition(), new XYsupport().createMovementVector((nextFood != null ? new XYsupport().createVector(nextFood.getPosition(), miniSquirrel.getPosition()) : null)));
            LOGGER.info(miniSquirrel + " hat Essensverfolgung aufgenommen");
        } else if (((nextSquirrel) != null && ((MasterSquirrel) nextSquirrel).isParent(miniSquirrel)) && miniSquirrel.getPosition().distanceFrom(nextSquirrel.getPosition()) < miniSquirrel.getVision()) {
            moveDirection = new XYsupport().setNewPosition(miniSquirrel.getPosition(), new XYsupport().createMovementVector(new XYsupport().createVector(nextSquirrel.getPosition(), miniSquirrel.getPosition())));
            LOGGER.info(miniSquirrel + " auf dem Weg nach hause");
        }

        //get intersecting Entity
        Entity intersectingEntity = flattenBoard[moveDirection.getY()][moveDirection.getX()];

        //if there actually is an intersecting entity
        if (intersectingEntity != null) {
            EntityType type = intersectingEntity.getEntityType();
            if (type == EntityType.Wall) {
                miniSquirrel.updateEnergy(intersectingEntity.getEnergy());
                miniSquirrel.setSuspensionCounter(3);
                LOGGER.info(miniSquirrel + " laeuft gegen die Wand");
            } else if (type == EntityType.GOOD_BEAST || type == EntityType.GOOD_PLANT || type == EntityType.BAD_PLANT) {
                miniSquirrel.updateEnergy(intersectingEntity.getEnergy());
                killAndReplace(intersectingEntity);
                LOGGER.info(miniSquirrel + " isst gerade " + intersectingEntity);
            } else if (type == EntityType.BAD_BEAST) {
                miniSquirrel.updateEnergy(intersectingEntity.getEnergy());
                ((BadBeast) intersectingEntity).setAmountBites(-1);
                LOGGER.info(miniSquirrel + " wurde gebissen");
            } else if (type == EntityType.MASTER_SQUIRREL) {
                if (((MasterSquirrel) intersectingEntity).isParent(miniSquirrel)) {
                    LOGGER.info(miniSquirrel + " übergibt Energy " + miniSquirrel.getEnergy());
                    intersectingEntity.updateEnergy(miniSquirrel.getEnergy());
                    kill(miniSquirrel);
                    return;
                } else {
                    kill(miniSquirrel);
                    LOGGER.info(miniSquirrel + " ist von " + intersectingEntity + " vernichtet worden");
                    return;
                }
            }
        } else {
            miniSquirrel.updatePosition(moveDirection);
            LOGGER.finest(miniSquirrel + " bewegt sich nach " + moveDirection + " verbleibende Energy " + miniSquirrel.getEnergy());
        }
        if (miniSquirrel.getEnergy() <= 0) {
            kill(miniSquirrel);
            LOGGER.info(miniSquirrel + " ist eines natuerlichen Todes gestorben");
        }

    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {

        //if anything intersecting at destination
        if (flattenBoard[moveDirection.getY()][moveDirection.getX()] != null) {
            Entity intersectingEntity = flattenBoard[moveDirection.getY()][moveDirection.getX()];
            EntityType type = intersectingEntity.getEntityType();
            if (type == EntityType.Wall) {
                masterSquirrel.updateEnergy(intersectingEntity.getEnergy());
                masterSquirrel.setSuspensionCounter(3);
                LOGGER.info(masterSquirrel + " laeuft gegen die Wand!");
            } else if (type == EntityType.BAD_BEAST || type == EntityType.BAD_PLANT || type == EntityType.GOOD_BEAST || type == EntityType.GOOD_PLANT) {
                masterSquirrel.updateEnergy(intersectingEntity.getEnergy());
                if (type == EntityType.BAD_BEAST) {
                    ((BadBeast) intersectingEntity).setAmountBites(-1);
                    LOGGER.info(masterSquirrel + " wurde gebissen");
                    if (((BadBeast) intersectingEntity).getRemainingBites() == 0) {
                        killAndReplace(intersectingEntity);
                    }
                } else {
                    killAndReplace(intersectingEntity);
                    LOGGER.info(masterSquirrel + " isst gerade " + intersectingEntity);
                }
            } else if (type == EntityType.MINI_SQUIRREL) {
                if (masterSquirrel.isParent((MiniSquirrel) intersectingEntity)) {
                    masterSquirrel.updateEnergy(intersectingEntity.getEnergy());
                    kill(intersectingEntity);
                    LOGGER.info(masterSquirrel + " hat Kind gefunden");
                } else {
                    kill(intersectingEntity);
                    LOGGER.info(masterSquirrel + " vernichtet fremdes Kind");
                }
            }
        } else {
            masterSquirrel.updatePosition(moveDirection);
            LOGGER.finest(masterSquirrel.toString() + " is moving to position " + moveDirection);
        }
    }

    @Override
    public EntityType getEntityType(int y, int x) {
        if (inWorld(x, y)) {
            if (flattenBoard[y][x] != null) {
                return flattenBoard[y][x].getEntityType();
            }
        }
        return null;
    }

    @Override
    public EntityType getEntityType(XY position) {
        if (inWorld(position.getX(), position.getY())) {
            if (flattenBoard[position.getY()][position.getX()] != null) {
                return flattenBoard[position.getY()][position.getX()].getEntityType();
            }
        }
        return null;
    }

    //simplifier to check whether coordinates are available
    private boolean inWorld(int x, int y) {
        return (y >= 0 && y <= getSize().getY()) && (x >= 0 && x <= getSize().getX());
    }

    //removes and replaces entity
    @Override
    public void killAndReplace(Entity entityToKill) {
        //remove entity
        board.getEntitySet().removeEntity(entityToKill);
        flattenBoard[entityToKill.getPosition().getY()][entityToKill.getPosition().getX()] = null;


        //replace entity
        XY temp = new XY(0, 0);
        if (entityToKill instanceof GoodPlant) {
            board.getEntitySet().addEntity(new GoodPlant(EntityType.GOOD_PLANT, 0, new XYsupport().getRandomPositionInWorld(getSize())));
        }
        if (entityToKill instanceof BadPlant) {
            board.getEntitySet().addEntity(new BadPlant(EntityType.BAD_PLANT, 0, new XYsupport().getRandomPositionInWorld(getSize())));
        }
        if (entityToKill instanceof GoodBeast) {
            board.getEntitySet().addEntity(new GoodBeast(EntityType.GOOD_BEAST, 0, new XYsupport().getRandomPositionInWorld(getSize())));
        }
        if (entityToKill instanceof BadBeast) {
            board.getEntitySet().addEntity(new BadBeast(EntityType.BAD_BEAST, 0, new XYsupport().getRandomPositionInWorld(getSize())));
        }
    }

    //removes entity
    @Override
    public void kill(Entity entityToKill) {
        //remove entity
        board.getEntitySet().removeEntity(entityToKill);
        flattenBoard[entityToKill.getPosition().getY()][entityToKill.getPosition().getX()] = null;
    }

    //provides board size
    @Override
    public XY getSize() {
        return board.getSize();
    }

}
