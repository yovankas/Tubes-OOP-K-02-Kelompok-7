package src.Tiles;

import src.Tile;
import src.Plants.Lilypad;

public class WaterTile extends Tile{
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
