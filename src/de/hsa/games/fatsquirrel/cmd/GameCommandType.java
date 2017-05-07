package de.hsa.games.fatsquirrel.cmd;

public enum GameCommandType implements CommandTypeInfo {

    HELP("help"," * list all commands"),
    EXIT("exit"," * exit program"),
    ALL("all"," * hilfetext zu all"),
    LEFT("a"," * hilfetext zu left"),
    UP("w"," * hilfetext zu up"),
    DOWN("s"," * hilfetext zu down"),
    RIGHT("d"," * hilfetext zu right"),
    MASTER_ENERGY("master_energy"," * hilfetext zu master_energy"),
    SPAWN_MINI("spawn_mini"," * hilfetext zu spawn_mini");

    private String command;
    private String help;
    private Class<?>[] var;

    GameCommandType(String command, String help, Class<?>... var){
        this.command = command;
        this.help = help;
        this.var = var;
    }

    @Override
    public String getName() {
        return this.command;
    }

    @Override
    public String getHelpText() {
        return this.help;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return this.var;
    }

}
