package de.hsa.games.fatsquirrel.cmd;

public interface CommandTypeInfo {

    String getName();

    String getHelpText();

    Class<?>[] getParamTypes();

}
