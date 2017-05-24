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

import java.io.IOException;
import java.util.*;

public class Launcher extends Application {

    private Timer timer = new Timer();
    private Scanner scanner = new Scanner(System.in);

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

    private void menu(String [] args, Launcher launcher){
        chooseGameType(launcher, args);

    }

    private void chooseGameType(Launcher launcher, String [] args){
        System.out.println("Choose gameType you want to play");
        System.out.println("1 >> for SinglePlayer mode without bot");
        System.out.println("2 >> for SinglePlayer mode without userInput");
        System.out.println("3 >> for SinglePLayer (against) a bot");
        System.out.println("4 >> exit this menu");
        int input = scanner.nextInt();
        MasterSquirrel hand = new HandOperatedMasterSquirrel(EntityType.HandOperatedMasterSquirrel, 0, new XY(1,1));
        MasterSquirrel bot = new MasterSquirrelBot(EntityType.MasterSquirrelBot, 0, new XY(1,1), "idk");
        if(input == 1){
            chooseUI(launcher,args, hand, null);
        }else if (input == 2){
            chooseUI(launcher,args, null, bot);
        }else if(input == 3){
            chooseUI(launcher,args, hand, bot);
        }else if(input == 4){
            System.exit(-1);
        }else{
            chooseGameType(launcher, args);
        }
    }


    private void chooseUI(Launcher launcher, String [] args, MasterSquirrel hand, MasterSquirrel bot){
        System.out.println("Please enter one of the following");
        System.out.println("1 >> to start game in console mode");
        System.out.println("2 >> to start game in graphic mode");
        System.out.println("3 >> exit this menu");
        int input = scanner.nextInt();
        if(input == 1){
            startConsoleGame(launcher, hand, bot);
        }else if (input == 2){
            startGUIGame(args);
        }else if(input == 3){
            System.exit(-1);
        }else{
            chooseUI(launcher, args, hand, bot);
        }
    }




    private void startConsoleGame(Launcher launcher, MasterSquirrel hand, MasterSquirrel bot){
        UI ui = new ConsoleUI();
        Board board = new Board(new BoardConfig());
        State state = new State(board);

        GameImpl gi = new GameImpl(state, hand, bot);
        gi.setUi(ui);

        launcher.startGame(gi);

        gi.run();
    }

    private void startGUIGame(String [] args){
        Application.launch(args);
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
        launcher.menu(args, launcher);
    }
}
