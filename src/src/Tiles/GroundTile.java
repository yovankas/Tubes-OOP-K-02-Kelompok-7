package src.Tiles;

import src.Plant;
import src.Tile;

public class GroundTile extends Tile{
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
