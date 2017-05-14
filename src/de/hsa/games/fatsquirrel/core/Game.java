package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.cmd.ScanException;
import de.hsa.games.fatsquirrel.cmd.UI;
import de.hsa.games.fatsquirrel.util.XY;

import java.io.IOException;

public abstract class Game {

    private static final int FPS = 5000;

    private UI ui;
    private State state;


    public Game(State state) {
        this.state = state;
    }

    public void run() {
        while (true) {
            render();

            try {
                Thread.sleep(FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                progressInput();
            } catch (ScanException e) {
//				e.printStackTrace();
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

    public abstract void render();

    public abstract void progressInput() throws IOException, ScanException;

    public void update() {
        state.update();


    }

}
