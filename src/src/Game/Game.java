package src.Game;

import java.util.Scanner;
import java.util.Timer;

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
                    start(scanner);
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

    public static void start(Scanner scanner) {
        Inventory inventory = new Inventory() ;

        while (!(inventory.getDeck().isDeckFull())) {
            inventory.showInventory();
            System.out.println("Masukkan angka tanaman untuk dimasukkan ke Deck: ");
            int i = scanner.nextInt();
            scanner.nextLine();
            try {
                inventory.addPlantToDeck(i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        if (inventory.getDeck().isDeckFull()) {
            System.out.println("Deck sudah selesai diisi. Apakah ingin mengganti? (Y/N)");
            inventory.getDeck().showDeck();
            String input = scanner.nextLine();
            if (input.equals("Y")) {
                //manggil change deck
            }
            else {
            }
        }

        
        Timer timer = new Timer();
        Map map = new Map(timer);

        scanner.close();
    }
}