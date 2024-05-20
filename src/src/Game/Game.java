package src.Game;

import java.util.Scanner;
// import src.Exception.WrongCommandException;

import src.Plants.Peashooter;

public class Game implements TimeObserver{
    GameTimer gameTimer;
    Map map;
    Sun sun;
    Deck deck;
    Inventory inventory;
    GameLogic gameLogic;
    boolean joever = false ;

    //constructor
    public Game(){
        this.gameTimer = new GameTimer();
        this.map = new Map(gameTimer);
        this.sun = new Sun(gameTimer);
        this.deck = new Deck(map);
        this.inventory = new Inventory(deck);
        this.gameLogic = new GameLogic(this, gameTimer);
    }

    //getter
    public Map getMap() {
        return this.map;
    }

    public Sun getSun() {
        return this.sun;
    }

    public void GameOver() {
        gameTimer.stop();
        joever = true ;
        System.out.println("Game Over");

    }

    public void Win() {
        gameTimer.stop();
        joever = true;
        System.out.println("You Win!");
    }

    public void gameDisplay(){

    }
    //main
    // public static void main(String[] args) {
    //     MainMenu mainMenu = new MainMenu();
    //     Scanner scanner = new Scanner(System.in);

    //     while (true) {
    //         mainMenu.printWelcomeMessage();
    //         String input = scanner.nextLine();

    //         try {
    //             if (input.equals("Start")) {
    //                 start(scanner);
    //                 break;
    //             } else if (input.equals("Help")) {
    //                 //help
    //             } else if (input.equals("Plants List")) {
    //                 mainMenu.printListOfPlants();
    //             } else if (input.equals("Zombies List")) {
    //                 mainMenu.printListOfZombies();
    //             } else if (input.equals("Exit")) {
    //                 mainMenu.printExit();
    //                 break;
    //             } else {
    //                 throw new WrongCommandException();
    //             }
    //         } catch (WrongCommandException e) {
    //             System.out.println(e.getMessage());
    //         }
    //     }

    //     scanner.close();
    // }

    public void start(Scanner scanner) {
        //deck
       // this.deck = new Deck(map);
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

        //start
        gameTimer.start();      
            Peashooter x = new Peashooter() ;
            inventory.getDeck().tanam(x, 3, 1);
            
        scanner.close();
    }

    @Override
    public void update(long elapsedTime) {
        gameDisplay();
    }
}