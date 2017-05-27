package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;
import de.hsa.games.fatsquirrel.util.XYsupport;

public class EntitySet {

    private Entity[] entities;

    private XY size;

    public EntitySet(XY size) {
        entities = new Entity[size.getY() * size.getX()];
        this.size = size;
    }

    //add entity to array
    public void addEntity(Entity newEntity) {
        if (isPositionInUse(newEntity.getPosition())) {
            newEntity.updatePosition(new XYsupport().getRandomPositionInWorld(this.size));
            this.addEntity(newEntity);
        } else {
            entities[getFreePosition()] = newEntity;
        }
    }

    //iterates to free position in array
    private int getFreePosition() {
        for (int position = 0; position < entities.length; position++) {
            if (entities[position] == null) {
                return position;
            }
        }
        return -1;
    }

    //checks whether position is already in use
    private boolean isPositionInUse(XY position) {
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null) {
                if (entities[i].getPosition().getY() == position.getY() && entities[i].getPosition().getX() == position.getX())
                    return true;
            }
        }
        return false;
    }

    //remove specific entity from array
    public void removeEntity(Entity entity) {
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null) {
                if (entities[i].equals(entity)) {
                    entities[i] = null;
                    return;
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

    public Entity[] collectRace(EntityType ... no) {
        Entity[] container = new Entity[entities.length];
        int counter = 0;

        //collects all of the provided entities in one array
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null) {
                for(int w = 0; w < no.length; w++)
                if (entities[i].getEntityType() == no[w]) {
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

    public Entity[] getEntities() {
        return entities;
    }


}
