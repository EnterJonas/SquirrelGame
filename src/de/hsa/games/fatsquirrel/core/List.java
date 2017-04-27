package de.hsa.games.fatsquirrel.core;

import java.util.Arrays;

public class List {

    private class ListElement {

        private Object data = null;
        private ListElement nextElement = null;
        private ListElement previousElement = null;


        private ListElement(ListElement nextElement, ListElement previousElement, Object data) {
            this.data = data;
            this.nextElement = nextElement;
            this.previousElement = previousElement;
            size++;
        }

        private Object getData() {
            return this.data;
        }

        private void setData(Object data) {
            this.data = data;
        }

        private ListElement getNextElement() {
            return this.nextElement;
        }

        private ListElement getPreviousElement() {
            return previousElement;
        }

        private void setPreviousElement(ListElement previousElement) {
            this.previousElement = previousElement;
        }

        private void setNextElement(ListElement nextElement) {
            this.nextElement = nextElement;
        }
    }

    private int size;
    private ListElement listHead;
    private ListElement listTail;
    private ListElement current;

    public List() {
        this.listHead = null;
        this.listTail = null;
        this.current = null;
    }

    //add new object to list
    public void add(Object data) {
        if (current == null) {
            current = new ListElement(null, null, data);
            listHead = current;
        } else {
            if (current.getNextElement() == null) {
                current.setNextElement(new ListElement(null, current, data));
                listTail = current.getNextElement();
                current = listTail;
            } else {
                ListElement temp = current.getNextElement();
                current.setNextElement(new ListElement(temp, current, data));
                temp.setPreviousElement(current.getNextElement());
                current = current.getNextElement();
            }
        }
    }

    //iterates current ot position in list where (if existent) removeEntity is located
    private void iterateCurrent(Entity removeEntity) {
        current = listTail;
        Entity helper = (Entity) current.getData();
        while (!helper.equals(removeEntity)) {
            current = current.getPreviousElement();
            helper = (Entity) current.getData();
        }
    }

    //removes ->current<- ListElement
    public void remove(Entity removeEntity) {
        iterateCurrent(removeEntity);
        if (current == listHead) {
            listHead = current.getNextElement();
            current.setPreviousElement(null);
            current = listHead;
        } else {
            if (current.getNextElement() == null) {
                current = current.getPreviousElement();
                current.setNextElement(null);
                listTail = current;
            } else {
                ListElement tempNext = current.getNextElement();
                current = current.getPreviousElement();
                current.setNextElement(tempNext);
                tempNext.setPreviousElement(current);
            }
        }
        current = listTail;
    }

    //returns amount of Objects stored in List
    public int getSize() {
        return this.size;
    }

    //returns true if provided Position already in use
    public boolean isIntersecting(XY position) {
        current = listHead;
        while (current != null) {
            Entity helper = (Entity) current.getData();
            if (helper.getPosition().getY() == position.getY() && helper.getPosition().getX() == position.getX()) {
                return true;
            }
            current = current.getNextElement();
        }
        current = listTail;
        return false;
    }

    //returns intersecting Object in case it's not the same type
    public Entity getIntersectingObject(XY position, Entity currentEntity) {
        current = listHead;
        while (current != null) {
            Entity helper = (Entity) current.getData();
            if (currentEntity != null) {
                if (helper.getPosition().getY() == position.getY() && helper.getPosition().getX() == position.getX() && helper.getEntityType() != currentEntity.getEntityType()) {
                    return helper;
                }
            } else {
                if (helper.getPosition().getY() == position.getY() && helper.getPosition().getX() == position.getX()) {
                    return helper;
                }
            }
            current = current.getNextElement();
        }
        current = listTail;
        return null;
    }

    //returns Entity from position in list
    public Entity getEntityAtPosition(int positionInList) {
        if (!isEmpty()) {
            current = listTail;
            for (int currentPosition = this.getSize(); currentPosition > positionInList; currentPosition--) {
                if (current != null)
                    current = current.getPreviousElement();
            }
            if (current != null) {
                return (Entity) current.getData();
            }
        }
        current = listTail;
        return null;
    }

    //returns Array of Squirrel existing inGame
    public Squirrel[] getSquirrelsInList() {
        Squirrel[] container = new Squirrel[getSize()];
        current = listHead;
        Entity helper;
        int counter = 0;
        while (current != null) {
            helper = (Entity) current.getData();
            if (helper.getEntityType() == EntityTypes.BotSquirrel || helper.getEntityType() == EntityTypes.HandOperatedMasterSquirrel || helper.getEntityType() == EntityTypes.MiniSquirrel) {
                container[counter++] = (Squirrel) helper;
            }
            current = current.getNextElement();
        }
        Squirrel[] squirrels = new Squirrel[counter];
        System.arraycopy(container, 0, squirrels, 0, squirrels.length);
        current = listTail;
        return squirrels;
    }

    public Entity[] getFood() {
        Entity[] container = new Entity[getSize()];
        current = listHead;
        Entity helper;
        int counter = 0;
        while (current != null) {
            helper = (Entity) current.getData();
            if (helper.getEntityType() == EntityTypes.GoodPlant || helper.getEntityType() == EntityTypes.GoodBeast) {
                container[counter++] = helper;
            }
            current = current.getNextElement();
        }
        Entity[] food = new Entity[counter];
        System.arraycopy(container, 0, food, 0, food.length);
        current = listTail;
        return food;
    }

    //checks listHead
    private boolean isEmpty() {
        return listHead == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "No Entities in World!";
        } else {
            ListElement helper = listHead;
            int currentPosition = 0;
            String[] container = new String[getSize()];
            while (helper != null) {
                Entity StringHelper = (Entity) helper.getData();
                container[currentPosition++] = StringHelper.toString();
                helper = helper.getNextElement();
            }
            return Arrays.toString(container);
        }
    }
}