package de.hsa.games.fatsquirrel.core;


public class GameImpl extends Game {

    public GameImpl(State state){
        super(state);
    }

    @Override
    protected void render() {
        for (int y = 0; y < boardView.getWorld().length; y++) {
            for (int x = 0; x < boardView.getWorld()[y].length; x++) {
                if (boardView.getWorld()[y][x] != null)
                    System.out.print(boardView.getWorld()[y][x]);
                else System.out.print(" ");
            }
            System.out.print('\n');
        }
    }

    @Override
    protected void progressInput() {
        moveDirection = new ConsoleUI().getCommand();
    }
}
