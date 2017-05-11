package de.hsa.games.fatsquirrel.core;


public class State {

    private Board board;
    private int highScore;

    public State(Board board) {
        this.board = board;
        update();
    }

    public Board getBoard() {
        return this.board;
    }

    public void update() {
        FlattenedBoard flattenedBoard = flattenedBoard();
        for (int i = 0; i < board.getSize().getY() * board.getSize().getX(); i++) {
            if (board.getEntitySet().getEntities() != null && board.getEntitySet().getEntities()[i] instanceof Character) {
                Entity current =  ((Character) board.getEntitySet().getEntities()[i]);
                ((Character) board.getEntitySet().getEntities()[i]).nextStep(flattenedBoard);
                flattenedBoard = flattenedBoard();
            }
        }
    }

    public FlattenedBoard flattenedBoard() {
        return new FlattenedBoard(board);
    }

}
