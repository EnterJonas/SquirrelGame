package de.hsa.games.fatsquirrel.core;

public abstract class Game {

    protected State state;
    protected XY moveDirection;
    BoardView boardView;


    public Game(State state){
        this.state = state;
        this.boardView = state.flattenedBoard();
        run();
    }

    public void run(){
        while(true){
            render();
            progressInput();
            update();
        }
    }

    protected abstract void render();

    protected abstract void progressInput();

    protected void update(){
        state.update();
        int position = state.flattenedBoard().getEntitySet().getSet().getSize();
        Entity current = state.flattenedBoard().getEntitySet().getSet().getEntityAtPosition(position);
        while(current instanceof Movable){
            ((Movable) current).nextStep(state.flattenedBoard());
            position--;
            current = state.flattenedBoard().getEntitySet().getSet().getEntityAtPosition(position);
        }

    }

}
