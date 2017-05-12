package de.hsa.games.fatsquirrel.util;


import de.hsa.games.fatsquirrel.cmd.ConsoleUI;
import de.hsa.games.fatsquirrel.cmd.FxUI;
import de.hsa.games.fatsquirrel.cmd.UI;
import de.hsa.games.fatsquirrel.core.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.*;


public class Launcher extends Application {

    private Timer timer = new Timer();


    public void startGame(Game game){
        timer.schedule(new PeriodicTask(game),0, 3000);
    }

    private class PeriodicTask extends TimerTask{

        GameImpl gameImpl;

        private PeriodicTask(Game game){
            this.gameImpl = (GameImpl) game;
        }

        @Override
        public void run() {
            gameImpl.render();
            gameImpl.update();
        }
    }


    public static void main (String[] args){
        Launcher launcher = new Launcher();

        UI ui = new ConsoleUI();
        Board board = new Board(new BoardConfig());
        State state = new State(board);

        GameImpl gi = new GameImpl(state);
        gi.setUi(ui);
        launcher.startGame(gi);

        gi.run();

        //Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("The game begins now...");
        BoardConfig boardConfig = new BoardConfig();

        Board board = new Board(boardConfig);

        State state = new State(board);

        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());

        final Game game = new GameImpl(state);

        game.setUi(fxUI);

        fxUI.setGameImpl((GameImpl) game);

        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Welcome to the virtual world of Squirrels.");
        primaryStage.setAlwaysOnTop(true);
        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent evt) {
                System.exit(-1);
            }

        });
        primaryStage.show();

        startGame(game);
    }



}
