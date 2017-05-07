package de.hsa.games.fatsquirrel.cmd;

public class NotEnoughEnergyException extends Exception {

    public NotEnoughEnergyException(String message){
        super(message);
    }
}