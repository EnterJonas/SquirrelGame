package de.hsa.games.fatsquirrel.core;

public abstract class Game {


    public void run(){
        while(true){
            render();
            progressInput();
            update();
        }
    }

    public abstract void render();

    public abstract void progressInput();

    protected void update(){

    }

}
