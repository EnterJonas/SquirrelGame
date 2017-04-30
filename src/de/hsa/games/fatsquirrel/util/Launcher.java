package de.hsa.games.fatsquirrel.util;


import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.core.GameImpl;
import de.hsa.games.fatsquirrel.core.State;

public class Launcher {

    public static void main (String[] args){
        //GameImpl game = new GameImpl();
        FlattenedBoard flattenedBoard = new FlattenedBoard();

        GameImpl game = new GameImpl(new State(flattenedBoard));

    }

}
