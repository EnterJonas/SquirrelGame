package de.hsa.games.fatsquirrel.util;


import de.hsa.games.fatsquirrel.cmd.ConsoleUI;
import de.hsa.games.fatsquirrel.cmd.UI;
import de.hsa.games.fatsquirrel.core.*;

public class Launcher {
    public static void main (String[] args){
        UI ui = new ConsoleUI();
        Board board = new Board(new BoardConfig());
        State state = new State(board);

        GameImpl gi = new GameImpl(state);

        gi.setUi(ui);
        gi.run();
    }
}
