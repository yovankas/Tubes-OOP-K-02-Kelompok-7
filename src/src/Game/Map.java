package src.Game;

import java.util.ArrayList;
import java.util.List;
import src.Zombie;
import src.Plant;
import src.Plants.*;

public class Map implements TimeObserver {
    private int length = 6;
    private int width = 11;
    private List<List<? extends Tile>> map;
    private boolean zombieAdded;
    private GameTimer gameTimer;

    // Constructor
    public Map(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
        map = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<Tile> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                if (j == 0) {
                    row.add(new BaseTile(i, j));
                } else if (j == width - 1) {
                    row.add(new SpawnTile(i, j));
                } else if ((i == 2 || i == 3) && (j >= 1 && j <= width - 2)) {
                    row.add(new WaterTile(i, j));
                } else {
                    row.add(new GroundTile(i, j));
                }
            }
            map.add(row);
        }
    }
    
    // Get tile
    public Tile getTile(int x, int y) {
        return map.get(x).get(y);
    }

    // Print map
    public void printMap() {
        System.out.println();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (map.get(i).get(j) instanceof GroundTile) {
                    System.out.print("G");
                } else if (map.get(i).get(j) instanceof WaterTile) {
                    System.out.print("W");
                } else if (map.get(i).get(j) instanceof BaseTile) {
                    System.out.print("B");
                } else if (map.get(i).get(j) instanceof SpawnTile) {
                    System.out.print("S");
                }
            }
            System.out.println();
        }
        System.out.println(); // Add a new line after printing the map
    }
    
    

    // Implement update method of TimeObserver interface
    @Override
    public void update(long elapsedTime) {
        // Check if 5 seconds have elapsed or if a zombie has been added
        if (elapsedTime % 5 == 0 || zombieAdded) {
            printMap();
            zombieAdded = false; // Reset the flag after printing the map
        }
    }

    public void addZombieToMap(int x, int y, Zombie zombie) {
        Tile tile = getTile(x, y);
        tile.addZombie(zombie);
        zombieAdded = true; // Set the flag to true when a zombie is added
    }
}


class Tile{
    private int x;
    private int y;
    private ArrayList<Zombie> listOfZombies;

    //constructor
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        listOfZombies = new ArrayList<Zombie>();
    }

    //get zombie
    public ArrayList<Zombie> getZombie(){
        return listOfZombies;
    }

    // getters
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    //add zombie
    public void addZombie(Zombie zombie){
        listOfZombies.add(zombie);
    }
}

class GroundTile extends Tile{
    private Plant plant;

    //constructor
    public GroundTile(int x, int y){
        super(x, y);
        this.plant = null;
    }

    //add plant
    public void addPlant(Plant plant){
        this.plant = plant;
    }

    //get plant
    public Plant getPlant(){
        return this.plant;
    }

    //remove plant
    public void removePlant(){
        this.plant = null;
    }
}

class WaterTile extends Tile{
    private Lilypad lilypad;

    //constructor
    public WaterTile(int x, int y){
        super(x, y);
        this.lilypad = null;
    }

    //add lilypad
    public void addLilypad(Lilypad lilypad){
        this.lilypad = lilypad;
    }

    //get lilypad
    public Lilypad getLilypad(){
        return this.lilypad;
    }

    //remove lilypad
    public void removeLilypad(){
        this.lilypad = null;
    }
}

class BaseTile extends Tile{
    //constructor
    public BaseTile(int x, int y){
        super(x, y);
    }
}

class SpawnTile extends Tile{
    //constructor
    public SpawnTile(int x, int y){
        super(x, y);
    }
}