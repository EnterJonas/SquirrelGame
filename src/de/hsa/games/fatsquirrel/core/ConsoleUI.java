package de.hsa.games.fatsquirrel.core;

import java.util.Scanner;

public class ConsoleUI implements UI {

    private Scanner scanner = new Scanner(System.in);



    @Override
    public XY getCommand() {
        char input = scanner.next().charAt(0);
        switch (input) {
            case 'w':
                return  new XY(-1, 0);
            case 's':
                return  new XY(1, 0);
            case 'a':
                return  new XY(0, -1);
            case 'd':
                return  new XY(0, 1);
            default:
                return  new XY(0, 0);
        }
    }

    @Override
    public void render() {

    }
}
