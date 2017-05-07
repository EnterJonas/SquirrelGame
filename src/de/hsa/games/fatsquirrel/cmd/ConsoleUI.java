package de.hsa.games.fatsquirrel.cmd;

import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.util.XY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleUI implements UI{

    CommandScanner commandScanner=new CommandScanner(GameCommandType.values(),new BufferedReader(new InputStreamReader(System.in)));

    @Override
    public void render(BoardView view) {
        for (int y = 0; y < view.getWorld().length; y++) {
            for (int x = 0; x < view.getWorld()[y].length; x++) {
                if (view.getWorld()[y][x] != null)
                    System.out.print(view.getWorld()[y][x]);
                else System.out.print(" ");
            }
            System.out.print('\n');
        }
    }

    @Override
    public Command getCommand() {
        try {
            return commandScanner.next();
        } catch (ScanException e) {
			//e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("FEHLER: falsches Argument");
        }
        return null;
    }

    @Override
    public void message(String msg) {

    }

}
