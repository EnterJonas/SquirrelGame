package de.hsa.games.fatsquirrel.util;

import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MasterSquirrelBot;

public class LaunchHelper {

    private static MasterSquirrelBot[] bots;
    private static HandOperatedMasterSquirrel masterSquirrel;

    public static void setMasterSquirrel(HandOperatedMasterSquirrel guidedMasterSquirrel){
        masterSquirrel = guidedMasterSquirrel;
    }

    public static void setBots(MasterSquirrelBot... botArray){
        bots = botArray;
    }

    public static HandOperatedMasterSquirrel getMasterSquirrel(){
        return masterSquirrel;
    }

    public static MasterSquirrelBot[] getBots(){
        return bots;
    }
}
