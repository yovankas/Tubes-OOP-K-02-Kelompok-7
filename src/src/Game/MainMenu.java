package src.Game;

import java.util.ArrayList;
import java.util.List;

import src.Plant;
import src.Zombie;
import src.Plants.*;
import src.Zombies.*;

public class MainMenu {
    private List<Zombie> listOfZombies;
    public List<Plant> listOfPlants;

    public MainMenu() {
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
        System.out.println("5. Exit");
    }

    public void printExit() {
        System.out.println("Bye!");
    }
}
