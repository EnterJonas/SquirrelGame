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
            if (board.getEntities() != null && board.getEntities()[i] instanceof Character) {
                ((Character) board.getEntities()[i]).nextStep(flattenedBoard);
            }
        }
    }

    public FlattenedBoard flattenedBoard() {
        return new FlattenedBoard(board);
    }

}
