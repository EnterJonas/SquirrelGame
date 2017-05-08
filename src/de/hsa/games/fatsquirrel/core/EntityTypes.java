package de.hsa.games.fatsquirrel.core;

public enum EntityTypes {
    BadBeast("B"),
    BadPlant("b"),
    GoodBeast("G"),
    GoodPlant("g"),
    MasterSquirrel("S"),
    MiniSquirrel("M"),
    BotSquirrel("S"),
    HandOperatedMasterSquirrel("S"),
    Wall("#");

    private String symbol;

    EntityTypes(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return this.symbol;
    }

}
