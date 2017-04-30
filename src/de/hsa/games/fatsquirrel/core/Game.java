package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.interfaces.BoardView;
import de.hsa.games.fatsquirrel.util.XY;

public abstract class Game {

    protected State state;
    protected XY moveDirection;
    BoardView boardView;


    public Game(State state) {
        this.state = state;
        this.boardView = state.flattenedBoard();
        run();
    }

    public void run() {
        while (true) {
            render();
            progressInput();
            update();
        }
    }

    protected abstract void render();

    protected abstract void progressInput();

    protected void update() {
        state.update();
        int position = state.flattenedBoard().getEntitySet().getSet().getSize();
        Entity current = state.flattenedBoard().getEntitySet().getSet().getEntityAtPosition(position);
        while (position > 1) {
            if (current instanceof Movable) {
                if (current.getEntityType() == EntityTypes.HandOperatedMasterSquirrel) {
                    ((HandOperatedMasterSquirrel)current).setInput(moveDirection);
                    ((Movable) current).nextStep(state.flattenedBoard());

                } else {
                    ((Movable) current).nextStep(state.flattenedBoard());

                }
            }
            current = state.flattenedBoard().getEntitySet().getSet().getEntityAtPosition(--position);
        }

    }

}
