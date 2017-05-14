package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.cmd.*;
import de.hsa.games.fatsquirrel.util.XY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameImpl extends Game {

    private HandOperatedMasterSquirrel handOperatedMasterSquirrel;
    private MasterSquirrelBot masterSquirrelBot;


    public GameImpl(State state) {
        super(state);
        this.handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(EntityTypes.HandOperatedMasterSquirrel, 0, new XY(1, 1));
        this.getState().getBoard().getEntitySet().addEntity(handOperatedMasterSquirrel);

        this.masterSquirrelBot = new MasterSquirrelBot(EntityTypes.MasterSquirrelBot, 0, new XY(1, 1), "idk");
        this.getState().getBoard().getEntitySet().addEntity(masterSquirrelBot);

    }

    @Override
    public void render() {
        this.getUi().render(this.getState().flattenedBoard());
    }

    @Override
    public void progressInput() throws IOException, ScanException {
        CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), new BufferedReader(new InputStreamReader(System.in)));

        Class<?> processorClass = null;

        try {
            processorClass = Class.forName(this.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Command command = commandScanner.next();

        Object[] params = command.getParams();

        GameCommandType commandType = (GameCommandType) command.getCommandType();

        Object[] castedParams = new Object[params.length];

        for (int i = 0; i < params.length; i++) {
            if (commandType.getParamTypes()[i] == int.class) {
                castedParams[i] = Integer.parseInt(String.valueOf(params[i]));
            } else if (commandType.getParamTypes()[i] == float.class) {
                castedParams[i] = Float.parseFloat((String) params[i]);
            } else {
                castedParams[i] = params[i];
            }
        }

        Method method = null;

        try {
            method = processorClass.getMethod(commandType.name().toLowerCase(), commandType.getParamTypes());
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        try {
            method.invoke(this, castedParams);
        } catch (NumberFormatException e) {
            System.err.println("FEHLER: falsches Argument");
        } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void help() {
        for (CommandTypeInfo cti : GameCommandType.values()) {
            System.out.println(">> " + cti.getName() + cti.getHelpText());
        }
        handOperatedMasterSquirrel.setInput(new XY(0, 0));
    }

    public void exit() {
        System.out.println("Exit");
        System.exit(0);
    }

    public void all() {
        System.out.println("ALL");

        handOperatedMasterSquirrel.setInput(new XY(0, 0));
    }

    public void left() {
        handOperatedMasterSquirrel.setInput(new XY(0, -1));
    }

    public void up() {
        handOperatedMasterSquirrel.setInput(new XY(-1, 0));
    }

    public void down() {
        handOperatedMasterSquirrel.setInput(new XY(1, 0));
    }

    public void right() {
        handOperatedMasterSquirrel.setInput(new XY(0, 1));
    }

    public String master_energy() {
        System.out.println("MASTER_ENERGY");

        if (handOperatedMasterSquirrel == null)
            return "Kein Eichhörnchen vorhanden";
        // masterSquirrel.setInput(new XY(0,0));
        return " Eichhörnchen \r\n Energie: " + handOperatedMasterSquirrel.getEnergy();
    }

    //mini currently spawns at random position in game...
    public void spawn_mini(int energy) {
        System.out.println("SPAWN_MINI");
        try {
            if (handOperatedMasterSquirrel.getEnergy() > energy) {
                MiniSquirrel miniSquirrel = new MiniSquirrel(EntityTypes.MiniSquirrel, energy, handOperatedMasterSquirrel.getPosition(), handOperatedMasterSquirrel);
                this.getState().getBoard().getEntitySet().addEntity(miniSquirrel);
                handOperatedMasterSquirrel.updateEnergy(-energy);
            } else {
                throw new NotEnoughEnergyException("Nicht genug Energie!");
            }
        } catch (NotEnoughEnergyException e) {
            System.out.println(e.getMessage());
        }
        handOperatedMasterSquirrel.setInput(new XY(0, 0));
    }

    public void spawn_mini(){
        System.out.println("SPAWN_MINI");
        int energy = 200;
        try {
            if (handOperatedMasterSquirrel.getEnergy() > energy) {
                MiniSquirrel miniSquirrel = new MiniSquirrel(EntityTypes.MiniSquirrel, energy, handOperatedMasterSquirrel.getPosition(), handOperatedMasterSquirrel);
                this.getState().getBoard().getEntitySet().addEntity(miniSquirrel);
                handOperatedMasterSquirrel.updateEnergy(-energy);
            } else {
                throw new NotEnoughEnergyException("Nicht genug Energie!");
            }
        } catch (NotEnoughEnergyException e) {
            System.out.println(e.getMessage());
        }
        handOperatedMasterSquirrel.setInput(new XY(0, 0));
    }

}

