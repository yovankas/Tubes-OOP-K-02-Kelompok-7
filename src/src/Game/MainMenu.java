package src.Game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import src.Plant;
import src.Plants.*;
import src.Zombie;
import src.Zombies.*;

public class MainMenu {
    private List<Zombie> listOfZombies;
    public List<Plant> listOfPlants;
    Inventory inventory;
    Deck deck;

    public MainMenu() {
        this.inventory = new Inventory(deck) ;

        // Inisialisasi daftar Zombie dan Plant saat objek MainMenu dibuat
        this.listOfZombies = new ArrayList<>();
        this.listOfPlants = new ArrayList<>() ;

        Zombie bucketheadZombie = new BucketheadZombie();
        listOfZombies.add(bucketheadZombie);

        Zombie coneheadZombie = new ConeheadZombie();
        listOfZombies.add(coneheadZombie);

        Zombie dolphinRiderZombie = new DolphinRiderZombie();
        listOfZombies.add(dolphinRiderZombie);

        Zombie duckyTubeZombie = new DuckyTubeZombie();
        listOfZombies.add(duckyTubeZombie);

        Zombie footballZombie = new FootballZombie();
        listOfZombies.add(footballZombie);

        Zombie newspaperZombie = new NewspaperZombie();
        listOfZombies.add(newspaperZombie);

        Zombie normalZombie = new NormalZombie();
        listOfZombies.add(normalZombie);

        Zombie poleVaultingZombie = new PoleVaultingZombie();
        listOfZombies.add(poleVaultingZombie);

        Zombie screenDoorZombie = new ScreenDoorZombie();
        listOfZombies.add(screenDoorZombie);

        Zombie bungeeZombie = new BungeeZombie();
        listOfZombies.add(bungeeZombie);

        Plant lilypad = new Lilypad();
        Plant magnetshroom = new Magnetshroom();
        Plant peashooter = new Peashooter();
        Plant repeater = new Repeater();
        Plant snowpea = new Snowpea() ;
        Plant squash = new Squash() ;
        Plant sunflower = new Sunflower();
        Plant tallnut = new Tallnut();
        Plant twinsunflower = new TwinSunflower();
        Plant wallnut = new Wallnut();
        listOfPlants.add(lilypad);
        listOfPlants.add(magnetshroom);
        listOfPlants.add(peashooter);
        listOfPlants.add(repeater);
        listOfPlants.add(snowpea);
        listOfPlants.add(squash);   
        listOfPlants.add(sunflower);
        listOfPlants.add(tallnut);
        listOfPlants.add(twinsunflower);
        listOfPlants.add(wallnut);
    }

    public void printListOfZombies() {
        for (Zombie zombie : listOfZombies) {
            zombie.printInfo();
            System.out.println("\n");
        }
    }

    public void printListOfPlants() {
        for (Plant plant : listOfPlants) {
            plant.printInfo();
            System.out.println("\n");
        }
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to Plants vs Zombies!"); //ntar ditambahin ascii
        System.out.println("Choose one!");
        System.out.println("1. Start");
        System.out.println("2. Help");
        System.out.println("3. Plants List");
        System.out.println("4. Zombies List");
        System.out.println("5. Change Inventory");
        System.out.println("6. Exit");
    }

    public void printExit() {
        System.out.println("Bye!");
    }

    public void printHelp() {
        System.out.println("======= Welcome to Michael vs Lalapan ! =======");
        System.out.println("Kamu bersama para lalapan akan bertahan melawan Michael yang sedang kelaparan");
        System.out.println("Tanam lalapan dan bertahan dari serangan Michael selama 160 detik !");
        System.out.println("===============================================");
        System.out.println("Available commands:");
        System.out.println("Start - Start the game");
        System.out.println("Help - Show available commands");
        System.out.println("Plants List - Show list of available plants");
        System.out.println("Zombies List - Show list of available zombies");
        System.out.println("Change Inventory - Swap order of plants in inventory");
        System.out.println("Exit - Exit the game");
        System.out.println("===============================================");
        System.out.println("Here are the available commands inside the game:");
        System.out.println("M - Show Map");
        System.out.println("D - Show Deck");
        System.out.println("T <X> <Y> <Deck No> - Plant lalapan from deck number <Deck No> to Tile (X, Y)");
        System.out.println("G <X> <Y> - Remove lalapan from Tile (X, Y)");
    }

    public void changeInventory() {
        Scanner sc = new Scanner(System.in);
        inventory.showInventory();
        System.out.println("Masukkan indeks inventory yang akan ditukar dengan format: x y");
    
        try{
            int x = sc.nextInt(); int y = sc.nextInt();
            inventory.swapInventory(x,y);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter two integers.");
            sc.nextLine();  // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred while swapping inventory: " + e.getMessage());
        }
    }
}
