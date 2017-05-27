package de.hsa.games.fatsquirrel.util;

import de.hsa.games.fatsquirrel.cmd.ConsoleUI;
import de.hsa.games.fatsquirrel.cmd.FxUI;
import de.hsa.games.fatsquirrel.cmd.UI;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.logger.MyLogger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Launcher extends Application {

    private Timer timer = new Timer();
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void startGame(Game game){
        timer.schedule(new PeriodicTask(game),0, 100);
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

    private void startConsoleGame(Launcher launcher){
        UI ui = new ConsoleUI();
        Board board = new Board(new BoardConfig());
        State state = new State(board);

        GameImpl gi = new GameImpl(state);
        gi.setUi(ui);

        launcher.startGame(gi);

        gi.run();
    }

    private void startGUIGame(String [] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("The game begins now...");
        BoardConfig boardConfig = new BoardConfig();

        Board board = new Board(boardConfig);

        State state = new State(board);

        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());

        final Game game = new GameImpl(state);

        game.setUi(fxUI);

        fxUI.setGameImpl((GameImpl) game);

        primaryStage.setScene(fxUI);
        primaryStage.setTitle("SquirrelGame");
        primaryStage.setAlwaysOnTop(true);
        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent evt) {
                System.exit(-1);
            }

        });
        primaryStage.show();

        startGame(game);
    }

    public static void main (String[] args){

        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }

        Launcher launcher = new Launcher();
        //launcher.startConsoleGame(launcher);
        launcher.startGUIGame(args);


    }
}
