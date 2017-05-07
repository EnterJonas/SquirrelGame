package de.hsa.games.fatsquirrel.util;


import de.hsa.games.fatsquirrel.cmd.ConsoleUI;
import de.hsa.games.fatsquirrel.cmd.UI;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.core.GameImpl;
import de.hsa.games.fatsquirrel.core.State;

public class Launcher {
    public static void main (String[] args){
        UI ui = new ConsoleUI();
        FlattenedBoard flattenedBoard = new FlattenedBoard();
        State state = new State(flattenedBoard);

        GameImpl gi = new GameImpl(state);

        gi.setUi(ui);
        gi.run();
    }
}
