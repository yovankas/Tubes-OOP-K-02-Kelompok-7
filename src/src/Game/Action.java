package src.Game;

import java.util.List;

import src.Tile;
import src.Zombie;
import src.Plants.Lilypad;
import src.Tiles.WaterTile;
import src.Tiles.*;

public class Action implements TimeObserver {
    private List<List<Tile>> map;
    private visitorGame visitorGame;

    //constructor
    public Action(GameTimer gameTimer, Map map) {
        gameTimer.addObserver(this);
        visitorGame = new visitorGame();
        this.map = map.getMap();
    }

    public void deadCollector(Tile tile) {
        List<Zombie> zombies = tile.getZombie();
        for (Zombie zombie : zombies) {
            if (zombie.isDead()) {
                tile.removeZombie(zombie);
            }
        }
        if (tile instanceof WaterTile) {
            //cast
            WaterTile waterTile = (WaterTile) tile;
            Lilypad lily = waterTile.getLilypad()
            if (lily != null){
                if (lily.isDead()) {
                    waterTile.removeLilypad();
                }
            }
        } else if (tile instanceof GroundTile) {
            //cast
            GroundTile groundTile = (GroundTile) tile;
            Plant plant = groundTile.getPlant();
            if (plant != null) {
                if (plant.isDead()) {
                    groundTile.removePlant();
                }
            }
        }
    }

    public void Advance

    @Override
    public void update(long elapsedTime) {
        synchronized (map) {
            for (int i = 0; i < map.size(); i++) {
                for (int j = 0; j < map.get(i).size(); j++) {
                    Tile tempTile = map.get(i).get(j);
                    if (tempTile instanceof WaterRile || tempTile instanceof GroundTile) {
                        deadCollector(tempTile);

                    }
                }
            }
        }
    }
}
