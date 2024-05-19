package src;

import java.util.ArrayList;
import src.Game.visitable;
import src.Game.visitor;

public class Tile implements visitable {
    private int x;
    private int y;
    private ArrayList<Zombie> listOfZombies;
    private Plant plant ;

    //constructor
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        listOfZombies = new ArrayList<>();
    }

    //get zombie
    public ArrayList<Zombie> getZombie(){
        return listOfZombies;
    }

    public Plant getPlant() {
        return plant ;
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

    public void addPlant(Plant plant) {
        this.plant = plant ;
    }

    public void removePlant() {
        this.plant = null ;
    }

    @Override
    public void accept(visitor v){
        v.visit(this);
    }
}
