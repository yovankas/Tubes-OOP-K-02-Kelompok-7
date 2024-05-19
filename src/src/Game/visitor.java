package src.Game;

import src.Tile;
import src.Tiles.*;

public interface visitor {
    public void visit(Tile tile);
    public void visit(GroundTile ground);
    public void visit(WaterTile water);
    public void visit(BaseTile base);
}
