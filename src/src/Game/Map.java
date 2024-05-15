package src.Game;

import java.util.ArrayList;
import java.util.List;
import src.*;

public class Map{
    private int length=11;
    private int width=6;
    private List<List<? extends Tile>> map;

    //constructor
    public Map(){
        map = new ArrayList<List<? extends Tile>>();
        for(int i = 0; i < length; i++){
            List<? extends Tile> row = new ArrayList<? extends Tile>();
            for(int j = 0; j < width; j++){
                switch(j){
                    case 0:
                        row.add(new BaseTile(i, j));
                        break;
                    case 10:
                        row.add(new SpawnTile(i, j));
                        break;
                    default:
                        if (i == 2 || i == 3){
                            row.add(new WaterTile(i, j));
                        }
                        else{
                            row.add(new GroundTile(i, j));
                        }
                        break;
                }
            }
            map.add(row);
        }
    }

    //get tile
    public Tile getTile(int x, int y){
        return map.get(x).get(y);
    }

    //print map simple
    public void printMap(){
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                if (map.get(i).get(j) instanceof GroundTile){
                    System.out.print("G");
                }
                else if (map.get(i).get(j) instanceof WaterTile){
                    System.out.print("W");
                }
                else if (map.get(i).get(j) instanceof BaseTile){
                    System.out.print("B");
                }
                else if (map.get(i).get(j) instanceof SpawnTile){
                    System.out.print("S");
                }
            }
            System.out.println();
        }
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