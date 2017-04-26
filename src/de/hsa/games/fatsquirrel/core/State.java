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
    }

    public FlattenedBoard flattenedBoard(){
        return this.board;
    }
}
