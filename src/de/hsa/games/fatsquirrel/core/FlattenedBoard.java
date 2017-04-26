package de.hsa.games.fatsquirrel.core;

import java.util.Random;

public class FlattenedBoard implements EntityContext, BoardView {

    private class Board {

        private BoardConfig boardConfig;
        private Entity[][] emptyWorld;
        private Entity[][] initWorld;
        private EntitySet entitySet;
        private int entityAmount;

        //Board constructor
        private Board() {
            this.boardConfig = new BoardConfig();
            this.emptyWorld = new Entity[boardConfig.getPitchHeight()][boardConfig.getPitchWidth()];
            this.initWorld = new Entity[boardConfig.getPitchHeight()][boardConfig.getPitchWidth()];
            this.entitySet = new EntitySet();
            this.entityAmount = boardConfig.getAmountEntities();
            createNewWorld();
            createInitWorld();
        }

        //creates empty World
        private void createNewWorld() {
            for (int y = 0; y < emptyWorld.length; y++) {
                for (int x = 0; x < emptyWorld[y].length; x++) {
                    if (y == 0 || x == 0 || y == (emptyWorld.length - 1) || x == (emptyWorld[y].length - 1)) {
                        entitySet.addEntity(EntityTypes.Wall, 0, new XY(y, x));
                    }
                }
            }
            populateWorld(entitySet.getSet().getSize(),emptyWorld);
        }

        //creates populated World
        private void createInitWorld() {
            initWorld = cloneWorld(emptyWorld);
            String[][] listingCopy = boardConfig.getEntityListing().clone();
            for (int z = 0; z < listingCopy.length; z++) {
                for (int w = 0; w < Integer.parseInt(listingCopy[z][1]); w++) {
                    createNewEntity(EntityTypes.valueOf(listingCopy[z][0]));
                }
            }
            populateWorld(this.entityAmount, initWorld);
            printWorld();
        }

        //creates new Entity, if position not already in use
        private void createNewEntity(EntityTypes entityType) {
            Random random = new Random();
            for (int i = 0; i < 1; i++) {
                int randomY = random.nextInt((initWorld.length - 2)) + 1;
                int randomX = random.nextInt((initWorld[0].length - 2)) + 1;
                XY randomPosition = new XY(randomY, randomX);
                if (!entitySet.getSet().isIntersecting(randomPosition)) {
                    entitySet.addEntity(entityType, 0, new XY(randomY, randomX));
                } else {
                    i--;
                }
            }
        }

        //writes world array
        private void populateWorld(int amountEntities, Entity[][] world){
            for(int position = entitySet.getSet().getSize(); position > (entitySet.getSet().getSize()-amountEntities); position--){
                Entity current = entitySet.getSet().getEntityAtPosition(position);
                XY cP = current.getPosition(); //cP: currentPosition
                world[cP.getY()][cP.getX()] = current;
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
        private void printWorld() {
            for (int x = 0; x < initWorld.length; x++) {
                for (int y = 0; y < initWorld[x].length; y++) {
                    if (initWorld[x][y] != null)
                        System.out.print(initWorld[x][y]);
                    else System.out.print(" ");
                }
                System.out.print('\n');
            }
        }

        //places Entities at their current position in world
        private void updateWorld() {
            initWorld = cloneWorld(emptyWorld);
            populateWorld(this.entityAmount, initWorld);
        }

        //returns or rather provides the world
        private Entity[][] flatten() {
            updateWorld();
            return initWorld;
        }

        private EntitySet getEntitySet(){
            return this.entitySet;
        }
    }

    private Board board;

    //constructor FlattenedBoard
    public FlattenedBoard(){
        board = new Board();
    }

    //returns nearest Squirrel
    public Squirrel nearestPlayerEntity(XY positionOfEntityLookingForPlayer){
        Squirrel [] squirrels = getBoard().getEntitySet().getSet().getSquirrelsInList();
        Squirrel tempSquirrel;
        for(int i = 1; i < squirrels.length; i++){
            for(int j = i; j > 0; j--){
                int squirrelVector1 = squirrels[j].getPosition().getSteps(positionOfEntityLookingForPlayer);
                int squirrelVector2 = squirrels[j-1].getPosition().getSteps(positionOfEntityLookingForPlayer);
                if(!(squirrelVector1== 0 && squirrelVector2 == 0))
                if(squirrelVector1 < squirrelVector2){
                    tempSquirrel = squirrels[j];
                    squirrels[j] = squirrels[j-1];
                    squirrels[j-1] = tempSquirrel;
                }
            }
        }
        return squirrels[0];
    }

    @Override
    public void tryMove() {

    }

    @Override
    public EntityTypes getEntityType(int y, int x) {
       return getWorld()[y][x].getEntityType();
    }

    //provides public access to board.obj
    public Board getBoard(){
        return board;
    }

    //provides public access to world
    public Entity[][] getWorld(){
        return getBoard().flatten();
    }

}
