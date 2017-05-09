package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.util.XY;

import java.lang.reflect.InvocationTargetException;

public class EntitySet {

    private Entity[] entities;

    private XY size;

    public EntitySet(XY size){
        entities = new Entity[size.getY()* size.getX()];
        this.size = size;
    }

    //add entity to array
    public void addEntity(Entity newEntity){
        if (isPositionInUse(newEntity.getPosition())) {
            newEntity.updatePosition(newEntity.getPosition().getRandomPositionInWorld(this.size));
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
            if (entities[i] != null && entities[i].getPosition().equals(position)) {
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

    public Entity[] getEntities(){
        return entities;
    }





}
