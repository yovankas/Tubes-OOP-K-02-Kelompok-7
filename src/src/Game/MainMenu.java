package src.Game;

import java.util.ArrayList;
import java.util.List;

import src.Zombie;
import src.Zombies.*;

public class MainMenu {
    private List<Zombie> listOfZombies;

    public MainMenu() {
        // Inisialisasi daftar Zombie dan Plant saat objek MainMenu dibuat
        this.listOfZombies = new ArrayList<>();

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
    }

    public void printListOfZombies() {
        for (Zombie zombie : listOfZombies) {
            zombie.printInfo();
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
