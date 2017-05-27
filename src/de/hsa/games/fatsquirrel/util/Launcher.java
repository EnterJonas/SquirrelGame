package de.hsa.games.fatsquirrel.util;

import de.hsa.games.fatsquirrel.cmd.ConsoleUI;
import de.hsa.games.fatsquirrel.cmd.FxUI;
import de.hsa.games.fatsquirrel.cmd.UI;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.logger.MyLogger;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Launcher extends Application {

    private Timer timer = new Timer();
    private Scanner scanner = new Scanner(System.in);
    private MasterSquirrelBot[] masterSquirrels;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private void menu(String[] args, Launcher launcher) {
        System.out.print("SquirrelGame[1=SinglePlayer 2=MultiPlayer vs. Bot 3=Zuschauermodus 4=Verlassen]");
        int selection = scanner.nextInt();
        if (selection == 1) {
            singlePlayer(args, launcher);
        } else if (selection == 2) {
            multiPlayer(args, launcher);
        } else if (selection == 3) {
            viewerMode(args, launcher);
        } else if (selection == 4) {
            System.exit(0);
        }
    }

    private void singlePlayer(String[] args, Launcher launcher) {
        LaunchHelper.setMasterSquirrel(new HandOperatedMasterSquirrel(EntityType.MASTER_SQUIRREL, 0, new XY(1, 1)));
        UISelection(args, launcher);
    }

    private void multiPlayer(String[] args, Launcher launcher) {
        System.out.print("SquirrelGame[Bitte geben Sie ein Zahl von 1 - 10 für die Anzahl der gegnerischen Bots ein]");
        checkAmountAndWriteArray();
        LaunchHelper.setMasterSquirrel(new HandOperatedMasterSquirrel(EntityType.MASTER_SQUIRREL, 0, new XY(1, 1)));
        UISelection(args, launcher);
    }

    private void checkAmountAndWriteArray(){
        int amount = 0;
        while (amount <= 0 || amount > 10) {
            amount = scanner.nextInt();
        }
        masterSquirrels = new MasterSquirrelBot[amount];
        for (int i = 0; i < amount; i++) {
            this.masterSquirrels[i] = new MasterSquirrelBot(EntityType.MASTER_SQUIRREL, 0, new XY(1, 1), "idk");
        }
        LaunchHelper.setBots(masterSquirrels);
    }

    private void viewerMode(String[] args, Launcher launcher) {
        System.out.print("SquirrelGame[Bitte geben Sie ein Zahl von 1 - 10 für die Anzahl der Bots ein]");
        checkAmountAndWriteArray();
        UISelection(args, launcher);
    }

    private void UISelection(String[] args, Launcher launcher) {
        System.out.print("SquirrelGame[1=Spiel auf Konsole 2=Spiel mit Grafischer Oberfläche 3=Verlassen]");
        int selection = scanner.nextInt();

        if (selection == 1) {
            startConsoleGame(launcher);
        } else if (selection == 2) {
            startGUIGame(args);
        } else if (selection == 3) {
            System.exit(0);
        }else UISelection(args, launcher);
    }

    private void startGame(Game game) {
        timer.schedule(new PeriodicTask(game), 0, 100);
    }

    private class PeriodicTask extends TimerTask {

        GameImpl gameImpl;

        private PeriodicTask(Game game) {
            this.gameImpl = (GameImpl) game;
        }

        @Override
        public void run() {
            gameImpl.render();
            gameImpl.update();
        }
    }

    private void startConsoleGame(Launcher launcher) {
        UI ui = new ConsoleUI();
        Board board = new Board(new BoardConfig());
        State state = new State(board);
        GameImpl gi = new GameImpl(state, LaunchHelper.getMasterSquirrel(), LaunchHelper.getBots());
        gi.setUi(ui);
        launcher.startGame(gi);
        gi.run();
    }

    private void startGUIGame(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("The game begins now...");
        BoardConfig boardConfig = new BoardConfig();
        Board board = new Board(boardConfig);
        State state = new State(board);
        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = new GameImpl(state, LaunchHelper.getMasterSquirrel(), LaunchHelper.getBots());
        game.setUi(fxUI);
        fxUI.setGameImpl((GameImpl) game);
        primaryStage.setScene(fxUI);
        primaryStage.setTitle("SquirrelGame");
        primaryStage.setAlwaysOnTop(true);
        fxUI.getWindow().setOnCloseRequest(evt -> System.exit(-1));
        primaryStage.show();
        startGame(game);
    }

    public static void main(String[] args) {
        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't create log files!");
        }
        Launcher launcher = new Launcher();
        launcher.menu(args, launcher);
        //launcher.startGUIGame(args);
    }

}