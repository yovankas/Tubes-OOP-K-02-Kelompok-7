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


    public void start(Scanner scanner) throws Exception {
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


        gameTimer.start(); 
        while (!joever) {
            String[] cmd = scanner.nextLine().split(" ");
            try {
                if (cmd[0].equalsIgnoreCase("T")) {
                    int xtanam = Integer.parseInt(cmd[1]);
                    int ytanam = Integer.parseInt(cmd[2]);
                    int indeksplant = Integer.parseInt(cmd[3]);
                    Plant plant = inventory.getDeck().getDeck()[indeksplant-1];
                    inventory.getDeck().tanam(plant, xtanam, ytanam, sun);
                } else if (cmd[0].equalsIgnoreCase("G")) {
                    int xgali = Integer.parseInt(cmd[1]);
                    int ygali = Integer.parseInt(cmd[2]);
                    inventory.getDeck().gali(xgali, ygali);
                } else if(cmd[0].equalsIgnoreCase("D")){
                    inventory.getDeck().showDeck();
                } else if(cmd[0].equalsIgnoreCase("M")){
                    gameDisplay();
                } else throw new WrongCommandException();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}