package src.Tiles;

import src.Game.visitor;
import src.Tile;

public class SpawnTile extends Tile{
    //constructor
    public SpawnTile(int x, int y){
        super(x, y);
    }

    @Override
    public void accept(visitor v) {
        v.visit(this);
    }
}
