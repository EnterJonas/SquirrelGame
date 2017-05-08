package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;

public class Board {

    private BoardConfig boardConfig;

    private XY size;

    private Entity[] entities;

    public Board(BoardConfig boardConfig) {
        this.boardConfig = boardConfig;
        this.size = new XY(boardConfig.getPitchHeight(), boardConfig.getPitchWidth());
        entities = new Entity[this.boardConfig.getPitchHeight() * this.boardConfig.getPitchWidth()];
        createBorderWalls();
        createInitCreatures();
    }

    public Entity[] getEntities() {
        return this.entities;
    }

    //creates boarder walls
    private void createBorderWalls() {
        for (int y = 0; y < boardConfig.getPitchHeight(); y++) {
            for (int x = 0; x < boardConfig.getPitchWidth(); x++) {
                if (y == 0 || x == 0 || y == (boardConfig.getPitchHeight() - 1) || x == (boardConfig.getPitchWidth() - 1)) {
                    addEntity(new Wall(EntityTypes.Wall, 0, new XY(y, x)));
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
                        addEntity(new BotSquirrel(EntityTypes.BotSquirrel, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case GoodPlant:
                        addEntity(new GoodPlant(EntityTypes.GoodPlant, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case BadPlant:
                        addEntity(new BadPlant(EntityTypes.BadPlant, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case GoodBeast:
                        addEntity(new GoodBeast(EntityTypes.GoodBeast, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case BadBeast:
                        addEntity(new BadBeast(EntityTypes.BadBeast, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                    case Wall:
                        addEntity(new Wall(EntityTypes.Wall, 0, temp.getRandomPositionInWorld(this.getSize())));
                        break;
                }
            }
        }
    }

    //iterates to free position in rray
    private int getFreePosition() {
        for (int position = 0; position < entities.length; position++) {
            if (entities[position] == null) {
                return position;
            }
        }
        return -1;
    }

    //checks whether position is already in use
    private boolean isPositionInUse(Entity entity) {
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null && entities[i].getPosition().equals(entity.getPosition())) {
                return true;
            }
        }
        return false;
    }

    //add entity to array
    public void addEntity(Entity newEntity) {
        if (isPositionInUse(newEntity)) {
            newEntity.updatePosition(newEntity.getPosition().getRandomPositionInWorld(this.size));
            this.addEntity(newEntity);
        } else {
            entities[getFreePosition()] = newEntity;
        }
    }

    //remove specific entity from array
    public void removeEntity(Entity entity) {
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null) {
                if (entities[i].equals(entity)) {
                    entities[i] = null;
                }
            }
        }
    }

    //return entity of position prov.
    public Entity getEntityAtPosition(XY position) {
        for (Entity entity : entities) {
            if (entity.getPosition().equals(position)) {
                return entity;
            }
        }
        return null;
    }

    //return board size
    public XY getSize() {
        return this.size;
    }

    public Entity[] collectRace(EntityTypes no1, EntityTypes no2, EntityTypes no3) {
        Entity[] container = new Entity[entities.length];
        int counter = 0;

        //collects all of the provided entities in one array
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null) {
                if (entities[i].getEntityType() == no1 || entities[i].getEntityType() == no2 || entities[i].getEntityType() == no3) {
                    container[counter++] = entities[i];
                }
            }
        }
        if (counter == 0) {
            return null;
        }
        Entity[] entities = new Entity[counter];
        System.arraycopy(container, 0, entities, 0, entities.length);
        return entities;
    }

    public Entity[][] flatten() {
        Entity[][] world = new Entity[this.boardConfig.getPitchHeight()][this.boardConfig.getPitchWidth()];
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null) {
                world[entities[i].getPosition().getY()][entities[i].getPosition().getX()] = entities[i];
            }
        }
        return world;
    }
}
