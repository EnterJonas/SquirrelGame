package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.interfaces.BoardView;
import de.hsa.games.fatsquirrel.interfaces.EntityContext;
import de.hsa.games.fatsquirrel.util.XY;

import java.util.Random;

public class FlattenedBoard implements EntityContext, BoardView {

    private class Board {

        private BoardConfig boardConfig;
        private Entity[][] emptyWorld;
        private Entity[][] initWorld;
        private EntitySet entitySet;

        //Board constructor
        private Board() {
            this.boardConfig = new BoardConfig();
            this.emptyWorld = new Entity[boardConfig.getPitchHeight()][boardConfig.getPitchWidth()];
            this.initWorld = new Entity[boardConfig.getPitchHeight()][boardConfig.getPitchWidth()];
            this.entitySet = new EntitySet();
            createNewWorld();
            createInitWorld();
        }

        //creates empty World
        private void createNewWorld() {
            for (int y = 0; y < emptyWorld.length; y++) {
                for (int x = 0; x < emptyWorld[y].length; x++) {
                    if (y == 0 || x == 0 || y == (emptyWorld.length - 1) || x == (emptyWorld[y].length - 1)) {
                        entitySet.addEntity(EntityTypes.Wall, 0, new XY(y, x), 0);
                    }
                }
            }
            String[][] listingCopy = boardConfig.getEntityListing().clone();
            for (int w = 0; w < Integer.parseInt((listingCopy[0][1])); w++) {
                createNewEntity(EntityTypes.valueOf(listingCopy[0][0]));
            }

            createWalls(emptyWorld);
        }

        //creates Wall entities for empty World
        private void createWalls(Entity[][] emptyWorld) {
            int position = entitySet.getSet().getSize();
            while (position > 0) {
                Entity current = entitySet.getSet().getEntityAtPosition(position--);
                XY cP = current.getPosition(); //cP: currentPosition
                emptyWorld[cP.getY()][cP.getX()] = current;
            }
        }

        //creates populated World
        private void createInitWorld() {
            initWorld = cloneWorld(emptyWorld);
            String[][] listingCopy = boardConfig.getEntityListing().clone();
            for (int z = 1; z < listingCopy.length; z++) {
                for (int w = 0; w < Integer.parseInt(listingCopy[z][1]); w++) {
                    createNewEntity(EntityTypes.valueOf(listingCopy[z][0]));
                }
            }
            populateWorld(initWorld);
        }

        //creates new Entity, if position not already in use
        private void createNewEntity(EntityTypes entityType) {
            Random random = new Random();
            for (int i = 0; i < 1; i++) {
                int randomY = random.nextInt((initWorld.length - 2)) + 1;
                int randomX = random.nextInt((initWorld[0].length - 2)) + 1;
                XY randomPosition = new XY(randomY, randomX);
                if (!entitySet.getSet().isIntersecting(randomPosition)) {
                    entitySet.addEntity(entityType, 0, new XY(randomY, randomX), 0);
                } else {
                    i--;
                }
            }
        }

        //writes world array
        private void populateWorld(Entity[][] world) {
            int position = entitySet.getSet().getSize();
            Entity current = entitySet.getSet().getEntityAtPosition(position);
            while (current.getEntityType() != EntityTypes.Wall) {
                current = entitySet.getSet().getEntityAtPosition(position);
                XY cP = current.getPosition(); //cP: currentPosition
                world[cP.getY()][cP.getX()] = current;
                position--;
            }
        }

        //clones Worlds (2D-Entity-Arrays)
        private Entity[][] cloneWorld(Entity[][] arrayToClone) {
            Entity[][] clonedArray = new Entity[arrayToClone.length][arrayToClone[0].length];
            for (int y = 0; y < emptyWorld.length; y++) {
                for (int x = 0; x < emptyWorld[y].length; x++) {
                    clonedArray[y][x] = arrayToClone[y][x];
                }
            }
            return clonedArray;
        }

        //prints the world (for testing purpose only)
//        private void printWorld() {
//            for (int x = 0; x < initWorld.length; x++) {
//                for (int y = 0; y < initWorld[x].length; y++) {
//                    if (initWorld[x][y] != null)
//                        System.out.print(initWorld[x][y]);
//                    else System.out.print(" ");
//                }
//                System.out.print('\n');
//            }
//        }

        //places entities at their current position in world
        private void updateWorld() {
            initWorld = cloneWorld(emptyWorld);
            populateWorld(initWorld);
        }

        //updates the world before returning it
        private Entity[][] flatten() {
            updateWorld();
            return initWorld;
        }

        //returns EntitySet
        private EntitySet getEntitySet() {
            return this.entitySet;
        }
    }

    private Board board;

    //constructor FlattenedBoard
    public FlattenedBoard() {
        board = new Board();
    }

    //returns nearest squirrel
    public Entity nearestEntity(XY positionOfEntityLookingForPlayer, EntityTypes no1, EntityTypes no2, EntityTypes no3) {
        Entity[] entities = getBoard().getEntitySet().getSet().getEntityList(no1,no2,no3);
        Entity temp;
        for (int i = 1; i < entities.length; i++) {
            for (int j = i; j > 0; j--) {
                int squirrelVector1 = entities[j].getPosition().getSteps(positionOfEntityLookingForPlayer);
                int squirrelVector2 = entities[j - 1].getPosition().getSteps(positionOfEntityLookingForPlayer);
                if (!(squirrelVector1 == 0 && squirrelVector2 == 0)) {
                    if (squirrelVector1 < squirrelVector2) {
                        temp = entities[j];
                        entities[j] = entities[j - 1];
                        entities[j - 1] = temp;
                    }
                }
            }
        }
        return entities[0];
    }

    //removes Entity from game
    public void kill(Entity entityToKill) {
        getEntitySet().getSet().remove(entityToKill);
    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        //position of nextSquirrel
        XY nextSquirrel = nearestEntity(badBeast.getPosition(), EntityTypes.HandOperatedMasterSquirrel, EntityTypes.BotSquirrel, EntityTypes.MiniSquirrel).getPosition();

        //if nextSquirrel is in range of bad-beast's vision
        if (nextSquirrel.getSteps(badBeast.getPosition()) < badBeast.getVision()) {
            //update moveDirection to attack the squirrel
            moveDirection = badBeast.getPosition().setNewPosition(badBeast.getPosition().createMovementVector(nextSquirrel.createVector(badBeast.getPosition())));
        }

        //get intersecting Entity
        Entity intersectingEntity = getEntitySet().getSet().getIntersectingObject(moveDirection, badBeast);

        //if there actually is an intersecting entity
        if (intersectingEntity != null) {
            EntityTypes type = intersectingEntity.getEntityType();
            if (type == EntityTypes.MiniSquirrel || type == EntityTypes.BotSquirrel || type == EntityTypes.HandOperatedMasterSquirrel) {
                intersectingEntity.updateEnergy(badBeast.getEnergy());
                badBeast.setAmountBites(-1);
                if (badBeast.getRemainingBites() == 0) {
                    killAndReplace(badBeast);
                }
            }
        } else {
            badBeast.updatePosition(moveDirection);
        }
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {

        //position of nextSquirrel
        XY nextSquirrel = nearestEntity(goodBeast.getPosition(), EntityTypes.HandOperatedMasterSquirrel, EntityTypes.BotSquirrel, EntityTypes.MiniSquirrel).getPosition();

        //if nextFood is in range of good-beast's vision
        if (nextSquirrel.getSteps(goodBeast.getPosition()) < goodBeast.getVision()) {
            //create negative movement vector in order to run away
            XY movementVector = goodBeast.getPosition().createMovementVector(nextSquirrel.createVector(goodBeast.getPosition()));
            XY negativeMovementVector = new XY(movementVector.getY() * -1, movementVector.getX() * -1);
            moveDirection = goodBeast.getPosition().setNewPosition(negativeMovementVector);
        }

        //if nothing is intersecting move there
        if (!(getEntitySet().getSet().isIntersecting(moveDirection))) {
            goodBeast.updatePosition(moveDirection);
        }
    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {

        //position of nextSquirrel
        XY nextFood = nearestEntity(miniSquirrel.getPosition(),EntityTypes.GoodBeast, EntityTypes.GoodPlant, null).getPosition();
        XY nextSquirrel = nearestEntity(miniSquirrel.getPosition(),EntityTypes.BotSquirrel, EntityTypes.HandOperatedMasterSquirrel, null).getPosition();

        //if nextSquirrel is in range of miniSquirrel's vision
        if (nextFood.getSteps(miniSquirrel.getPosition()) < miniSquirrel.getVision()) {
            //update moveDirection to collect food
            moveDirection = miniSquirrel.getPosition().setNewPosition(miniSquirrel.getPosition().createMovementVector(nextFood.createVector(miniSquirrel.getPosition())));
        }else if(nextSquirrel.getSteps(miniSquirrel.getPosition()) < miniSquirrel.getVision()){
            moveDirection = miniSquirrel.getPosition().setNewPosition(miniSquirrel.getPosition().createMovementVector(nextSquirrel.createVector(miniSquirrel.getPosition())));
        }

        //get intersecting Entity
        Entity intersectingEntity = getEntitySet().getSet().getIntersectingObject(moveDirection, miniSquirrel);

        //if there actually is an intersecting entity
        if (intersectingEntity != null) {
            EntityTypes type = intersectingEntity.getEntityType();
            if (type == EntityTypes.Wall) {
                miniSquirrel.updateEnergy(intersectingEntity.getEnergy());
                miniSquirrel.setSuspensionCounter(3);
            } else if (type == EntityTypes.GoodBeast || type == EntityTypes.GoodPlant || type == EntityTypes.BadPlant) {
                miniSquirrel.updateEnergy(intersectingEntity.getEnergy());
                killAndReplace(intersectingEntity);
            } else if (type == EntityTypes.BadBeast) {
                miniSquirrel.updateEnergy(intersectingEntity.getEnergy());
                ((BadBeast) intersectingEntity).setAmountBites(-1);
            } else if (type == EntityTypes.BotSquirrel || type == EntityTypes.HandOperatedMasterSquirrel) {
                if (((MasterSquirrel) intersectingEntity).isParent(miniSquirrel)) {
                    intersectingEntity.updateEnergy(miniSquirrel.getEnergy());
                    kill(miniSquirrel);
                } else {
                    kill(miniSquirrel);
                }
            }
        } else {
            miniSquirrel.updatePosition(moveDirection);
        }
        if (miniSquirrel.getEnergy() <= 0) {
            kill(miniSquirrel);
        }

    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {

        //if anything intersecting at destination
        if (getEntitySet().getSet().isIntersecting(moveDirection)) {
            Entity intersectingEntity = getEntitySet().getSet().getIntersectingObject(moveDirection, masterSquirrel);
            EntityTypes type = intersectingEntity.getEntityType();
            if (type == EntityTypes.Wall) {
                masterSquirrel.updateEnergy(intersectingEntity.getEnergy());
                masterSquirrel.setSuspensionCounter(3);
            } else if (type == EntityTypes.BadBeast || type == EntityTypes.BadPlant || type == EntityTypes.GoodBeast || type == EntityTypes.GoodPlant) {
                masterSquirrel.updateEnergy(intersectingEntity.getEnergy());
                if (type == EntityTypes.BadBeast) {
                    ((BadBeast) intersectingEntity).setAmountBites(-1);
                    if (((BadBeast) intersectingEntity).getRemainingBites() == 0) {
                        killAndReplace(intersectingEntity);
                    }
                } else {
                    masterSquirrel.updatePosition(moveDirection);
                    killAndReplace(intersectingEntity);
                }
            } else if (type == EntityTypes.MiniSquirrel) {
                if (masterSquirrel.isParent((MiniSquirrel) intersectingEntity)) {
                    masterSquirrel.updateEnergy(intersectingEntity.getEnergy());
                    kill(intersectingEntity);
                } else {
                    kill(intersectingEntity);
                }
            }
        } else {
            masterSquirrel.updatePosition(moveDirection);
        }
    }

    @Override
    public EntityTypes getEntityType(int y, int x) {
        return getWorld()[y][x].getEntityType();
    }

    //provides public access to board.obj
    public Board getBoard() {
        return board;
    }

    public EntitySet getEntitySet() {
        return getBoard().getEntitySet();
    }

    //provides public access to world !updated world!
    public Entity[][] getWorld() {
        return getBoard().flatten();
    }

    public void killAndReplace(Entity entityToKill) {
        getEntitySet().getSet().remove(entityToKill);
        getBoard().createNewEntity(entityToKill.getEntityType());
    }

    public void giveBirth(XY position, int parentID) {
        getBoard().entitySet.addEntity(EntityTypes.MiniSquirrel, 100, position, parentID);
    }

}
