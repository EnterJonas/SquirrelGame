package de.hsa.games.fatsquirrel.launcher;


import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.core.GameImpl;
import de.hsa.games.fatsquirrel.core.State;

public class Main {

    public static void main (String[] args){
        //GameImpl game = new GameImpl();
        FlattenedBoard flattenedBoard = new FlattenedBoard();

        GameImpl game = new GameImpl(new State(flattenedBoard));

    }

}
