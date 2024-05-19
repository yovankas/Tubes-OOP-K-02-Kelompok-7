package src.Tiles;

import src.Game.visitor;
import src.Tile;

public class BaseTile extends Tile {
    //constructor
    public BaseTile(int x, int y){
        super(x, y);
    }

    @Override
    public void accept(visitor v) {
        v.visit(this);
    }
}
