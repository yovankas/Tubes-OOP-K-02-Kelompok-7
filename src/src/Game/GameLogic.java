package src.Game;

import java.util.Iterator;
import java.util.List;
import src.Plant;
import src.Plants.Sunflower;
import src.Plants.TwinSunflower;
import src.Tile;
import src.Tiles.*;
import src.Zombie;

public class GameLogic implements visitor, TimeObserver {
    private final List<List<Tile>> map;
    int ZombieCount = 0;
    Sun sun;
    Game game;
    Map gameMap;

    //constructor
    public GameLogic(Game game, GameTimer gameTimer) {
        this.map = game.getMap().getMap();
        this.sun = game.getSun();
        this.game = game;
        this.gameMap = game.getMap();
        gameTimer.addObserver(this);
    } 

    public void deadZombieCollector(Tile tile){
        List<Zombie> zombies = tile.getZombie();
        Iterator<Zombie> iterator = zombies.iterator();
        while (iterator.hasNext()) {
            Zombie zombie = iterator.next();
            if (zombie.getHealth() <= 0) {
                iterator.remove();
                ZombieCount--;
            }
        }
    }

    public void zombieAttack(Tile tile){
        //plant and zombie is not null
        Plant plant;
        if (tile instanceof GroundTile){
            GroundTile ground = (GroundTile) tile;
            plant = ground.getPlant();
        } else { //WaterTile
            WaterTile water = (WaterTile) tile;
            plant = (Plant) water.getLilypad();
        }

        List<Zombie> zombies = tile.getZombie();
        Iterator<Zombie> iterator = zombies.iterator();
        // for (Zombie zombie : zombies) {
        while (iterator.hasNext()){
            Zombie zombie = iterator.next();
            if (zombie.getHas_Jump()){
                //jump
                Tile jumpTile = map.get(tile.getY()).get(tile.getX()-1);
                jumpTile.addZombie(zombie);
                // tile.removeZombie(zombie);
                iterator.remove();
                //kill jumped plant
                if (jumpTile instanceof GroundTile){
                    GroundTile ground = (GroundTile) jumpTile;
                    if (ground.getPlant() != null)ground.removePlant();
                } else if (jumpTile instanceof  WaterTile){ //WaterTile
                    WaterTile water = (WaterTile) tile;
                    if (water.getLilypad() != null)water.removeLilypad();
                }
            } else {
                zombie.attack(plant);
            }
        }
    }

    public void plantAttack(Tile tile){
        //plant is not null
        Plant plant;
        if (tile instanceof GroundTile){
            GroundTile ground = (GroundTile) tile;
            plant = ground.getPlant();
        } else { //WaterTile
            WaterTile water = (WaterTile) tile;
            plant = water.getLilypad().getPlant();
        }

        if (plant instanceof Sunflower || plant instanceof TwinSunflower){
            //generate sun
            long lifetime = (System.currentTimeMillis() - plant.getTimeCreated())/1000;
            if (lifetime % 3 == 0){ //every 3 seconds
                if (plant instanceof Sunflower){
                    sun.increaseSunAmount(25);
                } else { //TwinSunflower
                    sun.increaseSunAmount(50);
                }
            }
        } else {
            //identify range
            int range;
            switch (plant.getRange()){
                case -1:
                    range = gameMap.getLength() - 1;
                    break;
                default:
                    if (tile.getX() + plant.getRange() < gameMap.getLength() - 1){
                        range = tile.getX() + plant.getRange();
                    } else {
                        range = gameMap.getLength() - 1;
                    }
                    break;
            }

            //look for target
            Tile targetTile = null;
            if (tile.getZombie().isEmpty()){
                for (int i=tile.getX()+1; i< range; i++){
                    Tile tempTile = map.get(tile.getY()).get(i);
                    if (!tempTile.getZombie().isEmpty()){
                        targetTile = tempTile;
                        break;
                    }
                }
            } else {
                targetTile = tile;
            }

            //attack
            if (targetTile != null){
                List<Zombie> zombies = targetTile.getZombie();
                for (Zombie zombie : zombies) {
                    plant.attack(zombie);
                }
            }
        }
    }

    @Override
    public void visit(Tile tile) {
        // deadZombieCollector(tile);
        if (tile instanceof GroundTile){
            GroundTile ground = (GroundTile) tile;
            ground.accept(this);
        } else if (tile instanceof WaterTile){
            WaterTile water = (WaterTile) tile;
            water.accept(this);
        } else if (tile instanceof BaseTile){
            BaseTile base = (BaseTile) tile;
            base.accept(this);
        }
    }



    @Override
    public void visit(GroundTile ground) {
        // deadZombieCollector(ground);
        //check dead plants
        if (ground.getPlant() != null) {
            if(ground.getPlant().isDead()){     //there is dead plant
                ground.removePlant();
            } else if (!ground.getZombie().isEmpty()) { //there is plant and zombie
                plantAttack(ground);
                zombieAttack(ground);
            } else {                            //there is plant
                plantAttack(ground);
            }    
        }
    }

    @Override
    public void visit(WaterTile water) {
        // deadZombieCollector(water);
        //check dead lilypads
        if (water.getLilypad() != null) {
            if(water.getLilypad().isDead() || water.getLilypad().getPlant().isDead()){//there is dead lilypad
                water.removeLilypad();
            } else if (!water.getZombie().isEmpty()) { //there is lilypad and zombie
                if (water.getLilypad().getPlant() != null){ //there is lilypad and plant
                    plantAttack(water);
                }
                zombieAttack(water);
            } else { //there is lilypad
                if (water.getLilypad().getPlant() != null){ //there is lilypad and plant
                    plantAttack(water);
                }
            } 
        }
    }

    @Override
    public void visit(BaseTile base) {
        if (!base.getZombie().isEmpty()) {
            game.GameOver();
        }
    }

    @Override
    public void update(long elapsedTime) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                Tile tempTile = map.get(i).get(j);
                deadZombieCollector(tempTile);
                tempTile.accept(this);
            }
        }
        if (elapsedTime > 160 && ZombieCount == 0){
            game.Win();
        }
    }


}
