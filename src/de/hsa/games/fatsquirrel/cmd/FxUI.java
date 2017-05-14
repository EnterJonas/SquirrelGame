package de.hsa.games.fatsquirrel.cmd;

import de.hsa.games.fatsquirrel.core.GameImpl;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.EntityTypes;
import de.hsa.games.fatsquirrel.util.XY;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FxUI extends Scene implements UI {

    private static final int CELL_SIZE = 25;
    private Canvas boardCanvas;
    private Label msgLabel;
    private GameImpl gameimpl;

    public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
    }

    public static FxUI createInstance(XY boardSize) {
        Canvas boardCanvas = new Canvas(boardSize.getX() * CELL_SIZE,
                boardSize.getY() * CELL_SIZE);
        Label statusLabel = new Label();
        VBox top = new VBox();
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);
        statusLabel.setText("Hier werden Informationen angezeigt!");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);

        fxUI.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent keyEvent) {

                keyEvent.getCode();

                switch (keyEvent.getCode().toString()) {
                    case "A":
                        fxUI.gameimpl.left();
                        break;
                    case "S":
                        fxUI.gameimpl.down();
                        break;
                    case "D":
                        fxUI.gameimpl.right();
                        break;
                    case "W":
                        fxUI.gameimpl.up();
                        break;
                    case "Z":
                        fxUI.gameimpl.spawn_mini();
                        break;
                    case "E":
                        GridPane creditsPane = new GridPane();
                        creditsPane.getChildren().add(new Label(fxUI.gameimpl.master_energy()));
                        creditsPane.setMinSize(200, 400);
                        Scene creditsScene = new Scene(creditsPane);
                        Stage creditsStage = new Stage();
                        creditsStage.setScene(creditsScene);
                        creditsStage.setX(1300);
                        creditsStage.setY(300);
                        creditsStage.setResizable(false);
                        creditsStage.show();
                        fxUI.gameimpl.master_energy();
                        break;
                    case "H":
                        fxUI.gameimpl.help();
                        break;
                    case "Q":
                        fxUI.gameimpl.exit();
                        break;
                }

            }
        });

        return fxUI;
    }

    @Override
    public Command getCommand() {
        return null;
    }

    @Override
    public void render(final BoardView view) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                repaintBoardCanvas(view);
            }
        });

    }

    public void setGameImpl(GameImpl game) {
        this.gameimpl = game;
    }

    private void repaintBoardCanvas(BoardView view) {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        XY viewSize = view.getSize();

        // dummy for rendering a board snapshot, TODO: change it!
        // gc.fillText("Where are the beasts?", 100, 100);
        // gc.setFill(Color.RED);
        // gc.fillOval(viewSize.getX(),viewSize.getY(), 50, 50);

        // hier ergänzen
        for (int y = 0; y < viewSize.getY(); y++) {
            for (int x = 0; x < viewSize.getX(); x++) {
                if (view.getEntityType(y, x) == null) {
                } else {
                    if (view.getEntityType(y, x) == EntityTypes.Wall) {
                        gc.setFill(Color.YELLOW);
                        gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
                                CELL_SIZE);
                    }
                    if (view.getEntityType(y, x) == EntityTypes.HandOperatedMasterSquirrel) {
                        gc.setFill(Color.BLUE);
                        gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
                                CELL_SIZE);
                    }
                    if (view.getEntityType(y, x) == EntityTypes.BadBeast) {
                        gc.setFill(Color.RED);
                        gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
                                CELL_SIZE);
                    }
                    if (view.getEntityType(y, x) == EntityTypes.GoodBeast) {
                        gc.setFill(Color.GREEN);
                        gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
                                CELL_SIZE);
                    }
                    if (view.getEntityType(y, x) == EntityTypes.GoodPlant) {
                        gc.setFill(Color.GREEN);
                        gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
                                CELL_SIZE);
                    }
                    if (view.getEntityType(y, x) == EntityTypes.BadPlant) {
                        gc.setFill(Color.RED);
                        gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
                                CELL_SIZE);
                    }
                    if (view.getEntityType(y, x) == EntityTypes.MiniSquirrel) {
                        gc.setFill(Color.BLACK);
                        gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
                                CELL_SIZE);
                    }
                    if (view.getEntityType(y, x) == EntityTypes.MasterSquirrel) {
                        gc.setFill(Color.BROWN);
                        gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
                                CELL_SIZE);
                    }
                    if (view.getEntityType(y, x) == EntityTypes.MasterSquirrelBot) {
                        gc.setFill(Color.PURPLE);
                        gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE,
                                CELL_SIZE);
                    }
                }
            }
        }
    }

    @Override
    public void message(final String msg) {
        // TODO Auto-generated method stub
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(msg);
            }
        });

    }
}