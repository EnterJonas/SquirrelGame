package de.hsa.games.fatsquirrel.core;


public class Main {

    public static void main (String[] args){
        //GameImpl game = new GameImpl();
        FlattenedBoard flattenedBoard = new FlattenedBoard();
        flattenedBoard.getBoard();
        flattenedBoard.nearestPlayerEntity(new XY(5,5));
    }

}
