package de.hsa.games.fatsquirrel.cmd;

import de.hsa.games.fatsquirrel.core.BoardView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI implements UI{

    CommandScanner commandScanner=new CommandScanner(GameCommandType.values(),new BufferedReader(new InputStreamReader(System.in)));

    @Override
    public void render(BoardView view) {
        for (int y = 0; y < view.getSize().getY(); y++) {
            for (int x = 0; x < view.getSize().getX(); x++) {
                if (view.getEntityType(y,x) != null)
                    System.out.print(view.getEntityType(y,x).getSymbol());
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
