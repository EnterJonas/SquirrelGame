package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;

public class Board {

    private BoardConfig boardConfig;

    private EntitySet entitySet;

    private XY size;

    public Board(BoardConfig boardConfig) {
        this.boardConfig = boardConfig;
        this.size = new XY(boardConfig.getPitchHeight(), boardConfig.getPitchWidth());
        entitySet = new EntitySet(size);
        createBorderWalls();
        createInitCreatures();
    }

    //provides access to entitySet
    public EntitySet getEntitySet() {
        return this.entitySet;
    }

    //creates boarder walls
    private void createBorderWalls() {
        for (int y = 0; y < boardConfig.getPitchHeight(); y++) {
            for (int x = 0; x < boardConfig.getPitchWidth(); x++) {
                if (y == 0 || x == 0 || y == (boardConfig.getPitchHeight() - 1) || x == (boardConfig.getPitchWidth() - 1)) {
                    entitySet.addEntity(new Wall(EntityTypes.Wall, 0, new XY(y, x)));
                }
            }
        }
    }

    //creates entities listed in boardConfig
    private void createInitCreatures() {
        XY temp = new XY(0, 0);
        for (int i = 0; i < boardConfig.getEntityListing().length; i++) {
            for (int amount = 0; amount < Integer.parseInt(boardConfig.getEntityListing()[i][1]); amount++) {
                EntityTypes entityTypes = (EntityTypes.valueOf(boardConfig.getEntityListing()[i][0]));
                switch (entityTypes) {
                    case BotSquirrel:
                        entitySet.addEntity(new BotSquirrel(EntityTypes.BotSquirrel, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case GoodPlant:
                        entitySet.addEntity(new GoodPlant(EntityTypes.GoodPlant, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case BadPlant:
                        entitySet.addEntity(new BadPlant(EntityTypes.BadPlant, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case GoodBeast:
                        entitySet.addEntity(new GoodBeast(EntityTypes.GoodBeast, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case BadBeast:
                        entitySet.addEntity(new BadBeast(EntityTypes.BadBeast, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case Wall:
                        entitySet.addEntity(new Wall(EntityTypes.Wall, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                }
            }
        }
    }

    public XY getSize() {
        return this.size;
    }

    //provides board as 2D-array
    public Entity[][] flatten() {
        Entity[][] world = new Entity[this.boardConfig.getPitchHeight()][this.boardConfig.getPitchWidth()];
        for (int i = 0; i < entitySet.getEntities().length; i++) {
            if (entitySet.getEntities()[i] != null) {
                world[entitySet.getEntities()[i].getPosition().getY()][entitySet.getEntities()[i].getPosition().getX()] = entitySet.getEntities()[i];
            }
        }
        return world;
    }
}
