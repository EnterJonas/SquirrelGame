package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.cmd.ScanException;
import de.hsa.games.fatsquirrel.cmd.UI;
import de.hsa.games.fatsquirrel.util.XY;

import java.io.IOException;

public abstract class Game {

    private UI ui;



    protected State state;
    BoardView boardView;


    public Game(State state) {
        this.state = state;
        this.boardView = state.flattenedBoard();
    }

    public void run() {
        while (true) {
            render();
            try {
                progressInput();
            } catch (ScanException e) {
				//e.printStackTrace();
                System.err.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.err.println("FEHLER: falsches Argument");
            }

            update();
        }
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui){
        this.ui = ui;
    }

    public State getState(){
        return this.state;
    }

    protected abstract void render();

    protected abstract void progressInput() throws IOException, ScanException;

    protected void update() {
        state.update();


    }

}
