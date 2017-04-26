package de.hsa.games.fatsquirrel.core;

public abstract class Game {

    protected State state;
    protected XY moveDirection;


    public Game(State state){
        this.state = state;
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
    }

}
