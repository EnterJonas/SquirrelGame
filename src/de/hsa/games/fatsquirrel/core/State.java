package de.hsa.games.fatsquirrel.core;


public class State {

    private FlattenedBoard board;
    private int highScore;

    public State(FlattenedBoard board){
        this.board = board;
        update();
    }

    public void update(){
        flattenedBoard().getWorld();
        int position = flattenedBoard().getEntitySet().getSet().getSize();
        Entity current = flattenedBoard().getEntitySet().getSet().getEntityAtPosition(position);
        while (current.getEntityType() != EntityTypes.Wall) {
            if(current instanceof Movable) {
                ((Movable) current).nextStep(flattenedBoard());
            }
            current = flattenedBoard().getEntitySet().getSet().getEntityAtPosition(--position);
        }
    }

    public FlattenedBoard flattenedBoard(){
        return this.board;
    }

}
