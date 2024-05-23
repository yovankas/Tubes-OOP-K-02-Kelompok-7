package src.Game;

import java.util.Scanner;
import src.Exception.WrongCommandException;

public class main {
    //main
    public static void main(String[] args) throws Exception {
        Game game;
        MainMenu mainMenu = new MainMenu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mainMenu.printWelcomeMessage();
            String input = scanner.nextLine();

            try {
                if (input.equalsIgnoreCase("Start")) {
                    game = new Game();
                    game.start(scanner);
                } else if (input.equalsIgnoreCase("Help")) {
                    System.out.println("======= Welcome to Michael vs Lalapan ! =======");
                    System.out.println("Kamu bersama para lalapan akan bertahan melawan Michael yang sedang kelaparan");
                    System.out.println("Tanam lalapan dan bertahan dari serangan Michael selama 160 detik !");
                    System.out.println("===============================================");
                    System.out.println("Available commands:");
                    System.out.println("Start - Start the game");
                    System.out.println("Help - Show available commands");
                    System.out.println("Plants List - Show list of available plants");
                    System.out.println("Zombies List - Show list of available zombies");
                    System.out.println("Exit - Exit the game");
                    System.out.println("===============================================");
                    System.out.println("Here are the available commands inside the game:");
                    System.out.println("M - Show Map");
                    System.out.println("D - Show Deck");
                    System.out.println("T <X> <Y> <Deck No> - Plant lalapan from deck number <Deck No> to Tile (X, Y)");
                    System.out.println("G <X> <Y> - Remove lalapan from Tile (X, Y)");
                } else if (input.equalsIgnoreCase("Plants List") || input.equalsIgnoreCase("pl")) {
                    mainMenu.printListOfPlants();
                } else if (input.equalsIgnoreCase("Zombies List") || input.equalsIgnoreCase("zl")) {
                    mainMenu.printListOfZombies();
                } else if (input.equalsIgnoreCase("Exit")) {
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
