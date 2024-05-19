package src.Tiles;

import src.Plant;
import src.Tile;
import src.Plants.Lilypad;

public class WaterTile extends Tile{
    private Lilypad lilypad;
    private Plant plant;

    //constructor
    public WaterTile(int x, int y){
        super(x, y);
        this.lilypad = null;
        this.plant = null;
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
