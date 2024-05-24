package src;

import src.Game.*;
import java.util.Scanner;
import src.Exception.WrongCommandException;

public class main {
    //main
    public static void main(String[] args) throws Exception {
        Game game;
        MainMenu mainMenu = new MainMenu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            mainMenu.printWelcomeMessage();
            String input = scanner.nextLine();

            try {
                if (input.equalsIgnoreCase("Start")) {
                    game = new Game();
                    game.start(scanner);
                } else if (input.equalsIgnoreCase("Help")) {
                    System.out.println();
                    mainMenu.printHelp();
                } else if (input.equalsIgnoreCase("Plants List") || input.equalsIgnoreCase("pl")) {
                    mainMenu.printListOfPlants();
                } else if (input.equalsIgnoreCase("Zombies List") || input.equalsIgnoreCase("zl")) {
                    mainMenu.printListOfZombies();
                } else if (input.equalsIgnoreCase("Change Inventory") || input.equalsIgnoreCase("ci")) {
                    mainMenu.changeInventory();
                }
                    else if (input.equalsIgnoreCase("Exit")) {
                    mainMenu.printExit();
                    break;
                } else {
                    throw new WrongCommandException();
                }
            } catch (WrongCommandException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

}
