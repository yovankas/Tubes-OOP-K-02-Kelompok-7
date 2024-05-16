package src.Game;

import java.util.ArrayList;
import java.util.List;

import src.Plant;
import src.Zombie;
import src.Plants.Lilypad;


public class Map{
    private final int length=11;
    private final int width=6;
    private List<List<Tile>> map;

    //constructor
    public Map(){
        map = new ArrayList<List<Tile>>();
        for(int i=0; i<width; i++){
            List<Tile> row = new ArrayList<Tile>();
            for(int j=0; j<length; j++){
                if (j == 0) {
                    row.add(new BaseTile(j, i));
                } else if (j == length-1) {
                    row.add(new SpawnTile(j, i));
                } else if (i == 2 || i == 3) {
                    row.add(new WaterTile(j, i));
                } else {
                    row.add(new GroundTile(j, i));
                }
            }
            map.add(row);
        }
    }

    //getter
    public List<List<Tile>> getMap(){
        return map;
    }

    public int getLength(){
        return length;
    }

    public int getWidth(){
        return width;
    }


    //print map
    public void printMap(){
        for(int i=0; i<width; i++){
            for(int j=0; j<length; j++){
                if(map.get(i).get(j) instanceof BaseTile){
                    System.out.print("B ");
                }
                else if(map.get(i).get(j) instanceof SpawnTile){
                    System.out.print("S ");
                }
                else if(map.get(i).get(j) instanceof WaterTile){
                    System.out.print("W ");
                }
                else if(map.get(i).get(j) instanceof GroundTile){
                    System.out.print("G ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Map x = new Map();
        x.printMap();
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

    //remove zombie
    public void removeZombie(Zombie zombie){
        listOfZombies.remove(zombie);
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