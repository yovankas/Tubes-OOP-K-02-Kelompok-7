package src.Game;

import java.util.List;
import src.Tile;
import src.Tiles.*;
import src.Zombie;

public class visitorGame implements visitor, TimeObserver {
    private List<List<Tile>> map;
    int ZombieCount = 0;

    //constructor
    public visitorGame(Map map, GameTimer gameTimer) {
        this.map = map.getMap();
        gameTimer.addObserver(this);
    } 

    public void deadZombieCollector(Tile tile){
        List<Zombie> zombies = tile.getZombie();
        for (Zombie zombie : zombies) {
            if (zombie.getHealth() <= 0) {
                tile.removeZombie(zombie);
                ZombieCount--;
            }
        }
    }

    public void zombieAttack(Zombie zombie, Tile tile){
        List<Zombie> zombies = tile.getZombie();
        for (Zombie z : zombies) {
            if (z != zombie) {
                z.setHealth(z.getHealth() - zombie.getDamage());
            }
        }
    }

    @Override
    public void visit(Tile tile) {
        deadZombieCollector(tile);
        switch (tile) {
            case GroundTile ground -> ground.accept(this);
            case WaterTile water -> water.accept(this);
            case SpawnTile spawn -> spawn.accept(this);
            case BaseTile base -> base.accept(this);
            default -> {
            }
        }
    }



    @Override
    public void visit(GroundTile ground) {
        //check dead plants
        if (ground.getPlant() != null) {
            if(ground.getPlant().isDead()){
                ground.removePlant();
            }
        }

    }

    @Override
    public void visit(WaterTile water) {
        //check dead lilypads
        if (water.getLilypad() != null) {
            if(water.getLilypad().isDead()){
                water.removeLilypad();
            } else if (water.getLilypad().getPlant() != null) {

            }
        }
    }

    @Override
    public void visit(SpawnTile spawn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(BaseTile base) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void update(long elapsedTime) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                Tile tempTile = map.get(i).get(j);
                tempTile.accept(this);
            }
        }
    }


}
