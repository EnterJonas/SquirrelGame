package de.hsa.games.fatsquirrel.core;

public class EntitySet {

    private List list;

    public EntitySet() {
        list = new List();
    }

    public List getSet(){
        return list;
    }

    public void addEntity(EntityTypes entityType, int energy, XY position) {
        switch (entityType) {
            case BadBeast:
                list.add(new BadBeast(entityType, energy, position));
                break;
            case BadPlant:
                list.add(new BadPlant(entityType, energy, position));
                break;
            case BotSquirrel:
                list.add(new BotSquirrel(entityType, energy, position));
                break;
            case GoodBeast:
                list.add(new GoodBeast(entityType, energy, position));
                break;
            case GoodPlant:
                list.add(new GoodPlant(entityType, energy, position));
                break;
            case HandOperatedMasterSquirrel:
                list.add(new HandOperatedMasterSquirrel(entityType, energy, position));
                break;
            case MasterSquirrel:
                list.add(new HandOperatedMasterSquirrel(entityType, energy, position));
                break;
            case MiniSquirrel:
                list.add(new MiniSquirrel(entityType, energy, position));
                break;
            case Wall:
                list.add(new Wall(entityType, energy, position));
                break;
        }
    }

    public void removeEntity(Entity removeEntity){
        list.remove(removeEntity);
    }

    public void print() {
        System.out.print(list.toString());
    }
}
