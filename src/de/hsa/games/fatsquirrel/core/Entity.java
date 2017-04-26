package de.hsa.games.fatsquirrel.core;

enum EntityTypes {BadBeast, BadPlant, GoodBeast, GoodPlant, MasterSquirrel, MiniSquirrel, BotSquirrel, HandOperatedMasterSquirrel, Wall}

public abstract class Entity {

    private static int entityAmount = 0;
    private int UID = entityAmount;
    private int energy;
    private XY position;
    private EntityTypes entityType;

    public Entity(EntityTypes entityType, int energy, XY position) {
        this.entityType = entityType;
        this.energy = energy;
        this.position = position;
        entityAmount++;
    }

    public EntityTypes getEntityType(){
        return this.entityType;
    }

    public void updateEnergy(int deltaEnergy){
        this.energy += deltaEnergy;
    }

    public int getEnergy(){
        return this.energy;
    }

    public int getUID(){
        return this.UID;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Entity) {
            if (this.getUID() == ((Entity) (o)).getUID()) {
                return true;
            }
        }
        return false;
    }

    public XY getPosition(){
        return this.position;
    }

    public void updatePosition(XY position){
        this.position = position;
    }

}
