package src.Game;

import src.Plant;
import src.Exception.FullDeckException;
import src.Exception.InvalidIndexException;
import src.Exception.PlantAlreadyInDeckException;
import src.Plants.*;
import java.util.ArrayList;
// import java.util.Scanner;
import java.lang.Exception;



public class Inventory {

    private ArrayList<Plant> inventory ;
    private Deck deck ;

    public Inventory () {
        deck = new Deck() ;
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

    public void swapInventory (int x, int y)   {
        if (x < 0 || x > inventory.size() || y < 0 || y > inventory.size()) {
            System.out.println("Indeks tidak valid");
        } else {
            Plant temp =  inventory.get(x-1) ;
            inventory.set(x-1, inventory.get(y-1)) ;
            inventory.set(y-1, temp) ;
        }
    }

    public void swapDeck (int x, int y) throws InvalidIndexException {
        if (x < 0 || x > deck.getDeck().size() || y < 0 || y > deck.getDeck().size()) {
            throw new InvalidIndexException();
        } else {
            Plant temp = deck.getDeck().get(x);
            deck.getDeck().set(x, deck.getDeck().get(y));
            deck.getDeck().set(y, temp);
        }
    }

    public void deletePlantFromDeck (int index) throws InvalidIndexException{
        if (index < 0 || index > deck.getDeck().size()) {
            throw new InvalidIndexException();
        }
        else if (deck.getDeck().isEmpty()){
            System.out.println("Deck kosong");
        }
        else if (deck.getDeck().get(index) == null){
            System.out.println("Tidak ada tanaman di slot deck");
        }
        System.out.println(deck.getDeck().get(index).getName() + " sudah dihapus dari deck");
        deck.getDeck().remove(index);        
    }

    public void deletePlant (Plant plant){
        deck.getDeck().remove(plant);
    }

    public void addPlantToDeck (int i) throws Exception{
        if (i < 0 || i > inventory.size()) {
            throw new InvalidIndexException();
        }
        else if (deck.getDeck().size() >= 6){
            throw new FullDeckException();
        }
        else if (deck.getDeck().contains(inventory.get(i-1))){
            throw new PlantAlreadyInDeckException();
        }
        System.out.println(inventory.get(i-1).getName() + " ditambahkan ke Deck");
        deck.getDeck().add(inventory.get(i-1));
    }

    public void showInventory (){
        int i = 1;
        System.out.println("Inventory : ");
        for (Plant plant : inventory){
            System.out.println(i + ". " + inventory.get(i-1).getName());
            i++;
        }
    }



    // public static void main(String[] args) {
    //     Inventory inventory = new Inventory() ;
    //     Deck deck = new Deck() ;
    //     Scanner sc = new Scanner(System.in) ;
    //     inventory.showInventory();

    //     int x = 0 ;
    //     while (x < 6) {
    //     System.out.println("masukkan angka tanaman untuk dimasukkan ke deck: ");
    //     int i = sc.nextInt() ;
    //     try {
    //         inventory.addPlantToDeck(i);
    //         x++ ;
    //     } catch (Exception e){
    //         System.out.println(e.getMessage());
    //     }
    // }

    //     inventory.deck.showDeck();
    //     sc.close();
    // }

}
