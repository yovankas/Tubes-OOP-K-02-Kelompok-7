package src.Game;

import src.Plant;
import src.Plants.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Exception;



public class Inventory {
    // public List<Plant> listOfPlants = new ArrayList<>();
    // private List<Plant> deck ;
    private ArrayList<Plant> inventory ;
    private Deck deck ;

    // public Inventory (ArrayList<Plant> listofPlants) {
    //     for (Plant plant : listofPlants){
    //         this.listOfPlants.add(plant);
    //     }
    //     deck = new ArrayList<>() ;
    // }    
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
        if (x < 0 || x >= inventory.size() || y < 0 || y >= inventory.size()) {
            System.out.println("Indeks tidak valid");
        } else {
            Plant temp =  inventory.get(x-1) ;
            inventory.set(x-1, inventory.get(y-1)) ;
            inventory.set(y-1, temp) ;
        }
    }

    public void swapDeck (int x, int y){
        if (x < 0 || x >= deck.getDeck().size() || y < 0 || y >= deck.getDeck().size()) {
            throw new IllegalArgumentException("Index out of bounds");
        } else {
            Plant temp = deck.getDeck().get(x);
            deck.getDeck().set(x, deck.getDeck().get(y));
            deck.getDeck().set(y, temp);
        }
    }

    public void deletePlantFromDeck (int index){
        if (index < 0 || index >= deck.getDeck().size()) {
            throw new IllegalArgumentException("Index tidak valid");
        }
        else if (deck.getDeck().isEmpty()){
            System.out.println("Deck kosong");
        }
        else if (deck.getDeck().get(index) == null){
            System.out.println("Tidak ada tanaman di slot deck");
        }
        System.out.println(deck.getDeck().get(index).getName() + " has been deleted from deck");
        deck.getDeck().remove(index);        
    }

    public void deletePlant (Plant plant){
        deck.getDeck().remove(plant);
    }

    public void addPlantToDeck (int i) throws Exception{
        if (i < 0 || i >= inventory.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        else if (deck.getDeck().size() >= 6){
            throw new IllegalArgumentException("Deck is full");
        }
        else if (deck.getDeck().contains(inventory.get(i-1))){
            throw new IllegalArgumentException("Plant already in deck");
        }
        System.out.println(inventory.get(i-1).getName() + " ditambahkan deck");
        deck.getDeck().add(inventory.get(i-1));
    }

    public void showInventory (){
        int i = 1;
        System.out.println("Inventory : ");
        for (Plant p : inventory){
            System.out.println(i + ". " + inventory.get(i-1).getName());
            i++;
        }
    }



    public static void main(String[] args) {
        Inventory inventory = new Inventory() ;
        Deck deck = new Deck() ;
        Scanner sc = new Scanner(System.in) ;
        inventory.showInventory();
        System.out.println("masukkan angka tanaman untuk dimasukkan ke deck: ");
        int i = sc.nextInt() ;
        try {
            inventory.addPlantToDeck(i);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        inventory.deck.showDeck();
        
    }

}
