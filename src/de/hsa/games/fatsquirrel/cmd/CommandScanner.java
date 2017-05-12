package de.hsa.games.fatsquirrel.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {

    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputStream;

    public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader) {
        this.commandTypeInfos = commandTypes;
        this.inputReader = inputReader;
    }

    private boolean validateParams(Class<?>[] paramTypes, Object[] params, String[] tokens) {
        for (int i = 0; i < paramTypes.length; i++) {
            if (paramTypes[i].equals(int.class)) {
                params[i] = Integer.parseInt(tokens[i + 1]);
            } else if (paramTypes[i].equals(float.class)) {
                params[i] = Float.parseFloat(tokens[i + 1]);
            } else if (paramTypes[i].equals(String.class)) {
                params[i] = tokens[i + 1];
            } else {
                return false;
            }
        }
        return true;
    }

    public Command next() throws ScanException, IOException {

        String input;

        //remove unnecessary characters
        //System.out.print("Kommando eingeben: ");

        input=(inputReader.readLine()).trim();

        //split input by space
        String[] tokens = input.split(" ");


        for(CommandTypeInfo i : commandTypeInfos) {
            if(i.getName().equals(tokens[0])) {
                //check if input has needed amount of parameters
                if(i.getParamTypes().length==(tokens.length-1)) {
                    Object[] params=new Object[i.getParamTypes().length];
                    //if verification true, return command
                    if(validateParams(i.getParamTypes(),params,tokens)) {
                        return new Command(i,params);
                    }
                }
            }
        }
        throw new ScanException("FEHLER: Kommando existiert nicht");
    }

}
