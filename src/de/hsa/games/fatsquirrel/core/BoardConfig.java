package de.hsa.games.fatsquirrel.core;


public class BoardConfig {

    private String amountWall = "10";
    private String amountGoodBeast = "1";
    private String amountBadBeast = "1";
    private String amountBadPlant = "1";
    private String amountGoodPlant = "1";
    private String amountHandOperatedMasterSquirrel = "5";
    private String amountBotMasterSquirrel = "5";

    private int pitchWidth = 40;
    private int pitchHeight = 20;

    private int amountEntities = 0;


    public BoardConfig() {
        getEntityListing();
        calcAmountEntities();
    }

    //returns String Array with amount and Type of initial Entities
    public String[][] getEntityListing() {
        return new String[][]{{EntityTypes.Wall.toString(), amountWall},
                {EntityTypes.GoodBeast.toString(), amountGoodBeast},
                {EntityTypes.BadBeast.toString(), amountBadBeast},
                {EntityTypes.BadPlant.toString(), amountBadPlant},
                {EntityTypes.GoodPlant.toString(), amountGoodPlant},
                {EntityTypes.HandOperatedMasterSquirrel.toString(), amountHandOperatedMasterSquirrel},
                {EntityTypes.BotSquirrel.toString(), amountBotMasterSquirrel}};
    }

    //returns width of word
    public int getPitchWidth() {
        return pitchWidth;
    }

    //returns height of world
    public int getPitchHeight() {
        return pitchHeight;
    }

    //calculates amount of initial Entities
    private void calcAmountEntities(){
        for(int i = 0; i < getEntityListing().length; i++){
            amountEntities += Integer.parseInt(getEntityListing()[i][1]);
        }
    }

    //returns amount of initial Entities
    public int getAmountEntities(){
        return amountEntities;
    }
}
