package src.Game;

import java.util.Scanner;

public class Game {
    //main
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            mainMenu.printWelcomeMessage();
            String input = scanner.nextLine();
            if(input.equals("Start")) {
                //start
            }
            else if(input.equals("Help")) {
                //help
            }
            else if(input.equals("Plants List")) {
                //plants
            }
            else if(input.equals("Zombies List")) {
                mainMenu.printListOfZombies();
            }
            else if(input.equals("Exit")) {
                mainMenu.printExit();
                break;
            }
        }
        
        scanner.close();
    }
}