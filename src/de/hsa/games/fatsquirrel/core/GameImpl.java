package de.hsa.games.fatsquirrel.core;


public class GameImpl extends Game {

    private ConsoleUI consoleUI;
    private XY moveDirection;

    public GameImpl(){
        consoleUI = new ConsoleUI();
        run();
    }

    @Override
    public void render() {

    }

    @Override
    public void progressInput() {
        moveDirection = consoleUI.getCommand();
    }
}
