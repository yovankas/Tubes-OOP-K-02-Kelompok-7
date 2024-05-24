package src.Game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import src.Exception.FullDeckException;
import src.Exception.InvalidIndexException;
import src.Exception.PlantAlreadyInDeckException;
import src.Plant;
import src.Plants.*;



public class Inventory {

    private ArrayList<Plant> inventory ;
    private Deck deck ;

    public Inventory (Deck deck) {
        this.deck = deck ;
        this.inventory = new ArrayList<>() ;
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
        inventory.add(lilypad);
        inventory.add(magnetshroom);
        inventory.add(peashooter);
        inventory.add(repeater);
        inventory.add(snowpea);
        inventory.add(squash);   
        inventory.add(sunflower);
        inventory.add(tallnut);
        inventory.add(twinsunflower);
        inventory.add(wallnut);
    }

    public ArrayList<Plant> getInventory() {
        return inventory ;
    }

    public Deck getDeck() {
        return deck ;
    }

    public void swapInventory (int x, int y)   {
        if (x < 1 || x > inventory.size() || y < 1 || y > inventory.size()) {
            System.out.println("Indeks tidak valid");
        } else if (x == y) {
            System.out.println("Tidak bisa menukar dengan urutan yang sama");
        }else {
            Plant temp =  inventory.get(x-1) ;
            inventory.set(x-1, inventory.get(y-1)) ;
            inventory.set(y-1, temp) ;
            System.out.println("Berhasil menukar urutan tanaman.");
            showInventory();
        }
        
    }

    public void swapDeck (int x, int y) throws InvalidIndexException {
        if (x < 1 || x > deck.getDeck().length || y < 1 || y > deck.getDeck().length) {
            throw new InvalidIndexException();
        } else {
            Plant temp = deck.getDeck()[x-1];
            deck.getDeck()[x-1] = deck.getDeck()[y-1];
            deck.getDeck()[y-1] = temp;
        }
    }

    public void deletePlantFromDeck (int index) throws InvalidIndexException{
        if (index < 1 || index > deck.getDeck().length) {
            throw new InvalidIndexException();
        }
        else if (deck.isEmpty()){
            System.out.println("Deck kosong");
        }
        else if (deck.getDeck()[index - 1] == null){
            System.out.println("Tidak ada tanaman di slot deck");
        }
        else {
            System.out.println(deck.getDeck()[index - 1].getName() + " sudah dihapus dari Deck");
            deck.getDeck()[index - 1] = null;
            deck.decreasePlantCount();   
        }     
    }

    public void deletePlant (Plant plant){
        for (int i = 0; i < deck.getDeck().length; i++) {
            if (deck.getDeck()[i] == plant) {
                deck.getDeck()[i] = null;
                deck.decreasePlantCount();
                return;
            }
        }
    }

    public void addPlantToDeck (int i) throws Exception{
        if (i < 1 || i > inventory.size()) {
            throw new InvalidIndexException();
        }
        else if (deck.isDeckFull()){
            throw new FullDeckException();
        }
        else if (deck.containsPlant(inventory.get(i - 1))){
            throw new PlantAlreadyInDeckException();
        }
        for (int j = 0; j < deck.getDeck().length; j++) {
            if (deck.getDeck()[j] == null) {
                deck.getDeck()[j] = inventory.get(i - 1);
                deck.increasePlantCount();
                System.out.println(inventory.get(i - 1).getName() + " ditambahkan ke Deck");
                return;
            }
        }

    }

    public void showInventory (){
        int i = 1;
        System.out.println("Inventory : ");
        for (Plant plant : inventory){
            System.out.println(i + ". " + inventory.get(i-1).getName());
            i++;
        }
    }

    public void changeDeck(Scanner scanner) throws InvalidIndexException, PlantAlreadyInDeckException {
        // Scanner scanner = new Scanner(System.in);
        boolean stillChange = true;
        while (stillChange) {
            System.out.println();
            getDeck().showDeck();
            // System.out.println("Masukkan indeks tanaman di Deck yang ingin dihapus: ");
            // int deckIndex = scanner.nextInt();
            // scanner.nextLine(); // Clear the newline left by nextInt()
            
            // showInventory();
            // System.out.println("Masukkan indeks tanaman di Inventory yang ingin dimasukkan: ");
            // int inventoryIndex = scanner.nextInt();
            // scanner.nextLine(); // Clear the newline left by nextInt()

            int deckIndex = -1;
            int inventoryIndex = -1;
            
            while (true) {
                try {
                    System.out.println("Masukkan indeks tanaman di Deck yang ingin dihapus: ");
                    deckIndex = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline left by nextInt()
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
            
            showInventory();
            
            while (true) {
                try {
                    System.out.println("Masukkan indeks tanaman di Inventory yang ingin dimasukkan: ");
                    inventoryIndex = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline left by nextInt()
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            try {
                if (deckIndex < 1 || deckIndex > deck.getDeck().length) {
                    throw new InvalidIndexException();
                }

                if (inventoryIndex < 1 || inventoryIndex > inventory.size()) {
                    throw new InvalidIndexException();
                }

                Plant plantToRemove = deck.getDeck()[deckIndex - 1];
                if (plantToRemove == null) {
                    System.out.println("Tidak ada tanaman di slot deck ini");
                    continue;
                }

                Plant plantToAdd = inventory.get(inventoryIndex - 1);
                if (deck.containsPlant(plantToAdd)) {
                    throw new PlantAlreadyInDeckException();
                }

                deck.getDeck()[deckIndex - 1] = plantToAdd;
                System.out.println(plantToRemove.getName() + " sudah dihapus dari Deck dan " + plantToAdd.getName() + " ditambahkan ke Deck");

            } catch (InvalidIndexException e) {
                System.out.println( e.getMessage());
            } catch (PlantAlreadyInDeckException e) {
                System.out.println( e.getMessage());
            }
            while (true) {
                System.out.println("Apakah masih ingin mengganti Deck? (Y/N)");
                String stillChangeStr = scanner.nextLine();
                if (stillChangeStr.equalsIgnoreCase("N")) {
                    stillChange = false;
                    break;
                } else if (stillChangeStr.equalsIgnoreCase("Y")) {
                    break;
                } else {
                    System.out.println("Input tidak valid. Harap masukkan Y/N.");
                }
            }
            // System.out.println("Apakah masih ingin mengganti Deck? (Y/N)");
            // String stillChangeStr = scanner.nextLine();
            // if (stillChangeStr.equals("N")) {
            //     stillChange = false;
            }
        }

        // scanner.close();
    }


