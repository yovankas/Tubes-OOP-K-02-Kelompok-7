package src;

import java.util.ArrayList;

public class Tile {
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
