package src.Game;

import java.util.Scanner;

import src.Plant;
import src.Exception.FullDeckException;
import src.Exception.InvalidIndexException;
import src.Exception.PlantAlreadyInDeckException;
import src.Exception.WrongCommandException;
import src.Plants.Snowpea;


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
        gameTimer.printTime();
        map.printMap();
        sun.printSun();
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

    public void start(Scanner scanner) throws Exception {
        //deck
       // this.deck = new Deck(map);
        while (!(inventory.getDeck().isDeckFull())) {
            inventory.showInventory();
            System.out.println("Masukkan angka tanaman untuk dimasukkan ke Deck: ");
            int i = scanner.nextInt();
            scanner.nextLine();
            try {
                inventory.addPlantToDeck(i);
            } catch (InvalidIndexException e) {
                System.out.println("Indeks tidak valid: " + e.getMessage());
            } catch (FullDeckException e) {
                System.out.println("Deck sudah penuh: " + e.getMessage());
            } catch (PlantAlreadyInDeckException e) {
                System.out.println("Jenis tanaman sudah ada di dalam Deck: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Kesalahan: " + e.getMessage());
            }
        }

        if (inventory.getDeck().isDeckFull()) {
            System.out.println("Deck sudah selesai diisi. Apakah ingin mengganti? (Y/N)");
            inventory.getDeck().showDeck();
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                try {
                    inventory.changeDeck();
                } catch (InvalidIndexException | PlantAlreadyInDeckException e) {
                    System.out.println("Kesalahan saat mengganti Deck: " + e.getMessage());
                }
            }
        }

        //start
        gameTimer.start(); 
        Snowpea snowpea = new Snowpea();
        inventory.getDeck().tanam(snowpea, 2, 3, sun);
        while (true) {
            String[] cmd = scanner.nextLine().split(" ");
            try {
                if (cmd[0].equals("T")) {
                    int xtanam = Integer.parseInt(cmd[1]);
                    int ytanam = Integer.parseInt(cmd[2]);
                    int indeksplant = Integer.parseInt(cmd[3]);
                    Plant plant = inventory.getDeck().getDeck()[indeksplant-1];
                    inventory.getDeck().tanam(plant, xtanam, ytanam, sun);
                } else if (cmd[0].equals("G")) {
                    int xgali = Integer.parseInt(cmd[1]);
                    int ygali = Integer.parseInt(cmd[2]);
                    inventory.getDeck().gali(xgali, ygali);
                } else throw new WrongCommandException();
            } catch (WrongCommandException e) {
                System.out.println(e.getMessage());
            }
            break ;
        }

        // Menerima inputan cara kedua
        // while (true) {
        //     try {
        //         String cmd = scanner.nextLine();
        //         if (cmd.equals("Tanam")) { 
        //             System.out.println("Masukkan indeks kolom yang ingin ditanami!");
        //             int xtanam = scanner.nextInt();
        //             System.out.println("Masukkan indeks baris yang ingin ditanami!");
        //             int ytanam = scanner.nextInt();
        //             inventory.getDeck().showDeck();
        //             System.out.println("Masukkan indeks jenis tanaman yang ingin ditanam!");
        //             int indeksplant = scanner.nextInt();
        //             Plant plant= inventory.getDeck().getDeck()[indeksplant-1];
        //             inventory.getDeck().tanam(plant, xtanam, ytanam, sun);
        //         } else if (cmd.equals("Gali")) {
        //             System.out.println("Masukkan indeks kolom yang ingin digali!");
        //             int xgali = scanner.nextInt();
        //             System.out.println("Masukkan indeks baris yang ingin digali!");
        //             int ygali = scanner.nextInt();
        //             // inventory.getDeck().tanam(plant, xtanam, ytanam, sun); // method gali
        //         } else throw new WrongCommandException();
        //     } catch (WrongCommandException e) {
        //         System.out.println(e.getMessage());
        //     }
        //     break ;
        // }
        
    //  gameTimer.start();   

        while(!joever){
            if (scanner.next() == "\n"){

            } else {

            }
            gameDisplay();
        }
    }   
    //         Repeater y = new Repeater();
    //         Peashooter x = new Peashooter() ;
    //         Squash z = new Squash() ;
    //         Lilypad l = new Lilypad() ;
    //         l.addPlant(y);
    //         try {
    //         inventory.getDeck().tanam(z, 3, 1, sun);
    //         //inventory.getDeck().tanam(y, 4, 0, sun);
    //     }
    //         // inventory.getDeck().tanam(y, 4, 1, sun);
    //         // inventory.getDeck().tanam(y, 4, 4, sun);
    //         // inventory.getDeck().tanam(y, 4, 5, sun);
    //         // inventory.getDeck().tanam(x, 5, 0, sun);
    //         // inventory.getDeck().tanam(x, 5, 1, sun);
    //         // inventory.getDeck().tanam(x, 5, 4, sun);
    //         // inventory.getDeck().tanam(x, 5, 5, sun);
    //         // inventory.getDeck().tanam(z, 5, 0, sun);
    //         // inventory.getDeck().tanam(z, 5, 1, sun);
    //         // inventory.getDeck().tanam(z, 5, 4, sun);
    //         // inventory.getDeck().tanam(z, 5, 5, sun);
    //         // inventory.getDeck().tanam(l, 5, 2, sun);
    //         // inventory.getDeck().tanam(l, 5, 3, sun);}
    //         catch(Exception e){System.out.println(e.getMessage());} ;
            
    // }

    @Override
    public void update(long elapsedTime) {
        gameDisplay();
    }
}