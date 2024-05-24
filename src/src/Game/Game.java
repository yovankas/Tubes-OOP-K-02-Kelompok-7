package src.Game;

import java.util.InputMismatchException;
import java.util.Scanner;
import src.Exception.InvalidIndexException;
import src.Exception.PlantAlreadyInDeckException;
import src.Exception.WrongCommandException;
import src.Plant;


public class Game {
    GameTimer gameTimer;
    Map map;
    Sun sun;
    Deck deck;
    Inventory inventory;
    GameLogic gameLogic;
    boolean joever = false ;

    //constructor
    // public Game(){
    //     this.gameTimer = new GameTimer();
    //     this.map = new Map(gameTimer);
    //     this.sun = new Sun(gameTimer);
    //     this.deck = new Deck(map);
    //     this.inventory = new Inventory(deck);
    //     this.gameLogic = new GameLogic(this, gameTimer);
    //     this.joever = false;
    // }

    public Game(){
        initializeGame();
    }

    // Initialize or reset the game state
    private void initializeGame() {
        this.gameTimer = new GameTimer();
        this.map = new Map(gameTimer);
        this.sun = new Sun(gameTimer);
        this.deck = new Deck(map);
        this.inventory = new Inventory(deck);
        this.gameLogic = new GameLogic(this, gameTimer);
        this.joever = false;
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
        System.out.println();
        System.out.println("Game Over");
        System.out.println("Press any key to exit...");
    }

    public void Win() {
        gameTimer.stop();
        joever = true;
        System.out.println();
        System.out.println("You Win!");
    }

    public void gameDisplay(){
        gameTimer.printTime();
        map.printMap();
        sun.printSun();
    }

    public void gameHelp(){
        System.out.println("Here are the available commands:");
        System.out.println("M - Show Map");
        System.out.println("D - Show Deck");
        System.out.println("T <X> <Y> <Deck No> - Plant lalapan from deck number <Deck No> to Tile (X, Y)");
        System.out.println("G <X> <Y> - Remove lalapan from Tile (X, Y)");
    }


    public void start(Scanner scanner) throws Exception {

        initializeGame();
        
        while (!(inventory.getDeck().isDeckFull())) {
            inventory.showInventory();
            System.out.println("Masukkan angka tanaman untuk dimasukkan ke Deck: ");
            try {
                int i = scanner.nextInt();
                scanner.nextLine();
                inventory.addPlantToDeck(i);
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa integer. Silakan coba lagi.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (inventory.getDeck().isDeckFull()) {
            // String input = scanner.nextLine();
            // if (input.equalsIgnoreCase("Y")) {
            //     try {
            //         inventory.changeDeck(scanner);
            //     } catch (InvalidIndexException | PlantAlreadyInDeckException e) {
            //         System.out.println("Kesalahan saat mengganti Deck: " + e.getMessage());
            //     }
            // }
            while (true) {
                System.out.println("Deck sudah selesai diisi. Apakah ingin mengganti? (Y/N)");
                inventory.getDeck().showDeck();
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("N")) {
                    break;
                } else if (input.equalsIgnoreCase("Y")) {
                    try {
                            inventory.changeDeck(scanner);
                        } catch (InvalidIndexException | PlantAlreadyInDeckException e) {
                            System.out.println("Kesalahan saat mengganti Deck: " + e.getMessage());
                        }
                    break;
                } else {
                    System.out.println("Input tidak valid. Harap masukkan Y/N.");
                }
            }
        }


        gameTimer.start(); 
        gameDisplay();
        while (!joever) {
            String[] cmd = scanner.nextLine().split(" ");
            try {
                if (cmd[0].equalsIgnoreCase("T") || cmd[0].equalsIgnoreCase("tanam")) {
                    int xtanam = Integer.parseInt(cmd[1]);
                    int ytanam = Integer.parseInt(cmd[2]);
                    int indeksplant = Integer.parseInt(cmd[3]);
                    try {
                        Plant plant = inventory.getDeck().getNewPlant(indeksplant-1);
                        inventory.getDeck().tanam(plant, xtanam, ytanam, sun);
                        gameDisplay();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid Deck index: " + (indeksplant));
                    } catch (NullPointerException e) {
                        System.out.println("Deck is not properly initialized");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    
                } else if (cmd[0].equalsIgnoreCase("G") || cmd[0].equalsIgnoreCase("gali")) {
                    int xgali = Integer.parseInt(cmd[1]);
                    int ygali = Integer.parseInt(cmd[2]);
                    inventory.getDeck().gali(xgali, ygali);
                    gameDisplay();
                } else if(cmd[0].equalsIgnoreCase("D") || cmd[0].equalsIgnoreCase("deck")){
                    inventory.getDeck().showDeck();
                } else if(cmd[0].equalsIgnoreCase("M") || cmd[0].equalsIgnoreCase("map")){
                    gameDisplay();
                } else if(cmd[0].equalsIgnoreCase("H") || cmd[0].equalsIgnoreCase("help")){
                    gameHelp();
                } else {
                    if (!joever) throw new WrongCommandException();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}