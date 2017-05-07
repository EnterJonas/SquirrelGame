package de.hsa.games.fatsquirrel.cmd;

import de.hsa.games.fatsquirrel.core.BoardView;


public interface UI {

    void render(BoardView view);
    Command getCommand();
    void message(String msg);


}
