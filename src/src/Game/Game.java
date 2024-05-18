package src.Game;

import java.util.Scanner;

import src.Exception.WrongCommandException;

public class Game {
    //main
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mainMenu.printWelcomeMessage();
            String input = scanner.nextLine();

            try {
                if (input.equals("Start")) {
                    start();
                    break;
                } else if (input.equals("Help")) {
                    //help
                } else if (input.equals("Plants List")) {
                    mainMenu.printListOfPlants();
                } else if (input.equals("Zombies List")) {
                    mainMenu.printListOfZombies();
                } else if (input.equals("Exit")) {
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

    public static void start() {
        
    }
}