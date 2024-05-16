package src.Game;

import java.util.Scanner;
import java.util.Timer;

import src.Zombie;
import src.Zombies.NormalZombie;

public class Game {
    //main
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            mainMenu.printWelcomeMessage();
            String input = scanner.nextLine();
            if(input.equals("Start")) {
                Timer timer = new Timer();
                // Create a GameTimer instance using the existing Timer
                GameTimer gameTimer = new GameTimer(timer);
                // Create a Map instance and pass the GameTimer instance
                Map map = new Map(gameTimer);
                // Register Map as an observer to GameTimer
                gameTimer.addObserver(map);
                // Start the timer
                gameTimer.start();
                Zombie zombie = new NormalZombie(); // Create a new Zombie object
                map.addZombieToMap(0, 0, zombie);

                break;
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