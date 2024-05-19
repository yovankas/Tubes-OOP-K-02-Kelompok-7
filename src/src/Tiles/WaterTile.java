package src.Tiles;

import src.Game.visitor;
import src.Plants.Lilypad;
import src.Tile;

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

    @Override
    public void accept(visitor v) {
        v.visit(this);
    }
}
