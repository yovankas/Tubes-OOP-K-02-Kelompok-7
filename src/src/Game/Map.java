package src.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import src.Plant;
import src.Tile;
import src.Tiles.*;
import src.Zombie;
import src.Zombies.*;

class MapSpawner {
    private List<List<Tile>> map;
    private Random random;
    private static final int MAX_ZOMBIES = 10;
    private static final double SPAWN_PROBABILITY = 0.3;
    private long lastSpawnTime = 0;

    public MapSpawner(List<List<Tile>> map) {
        this.map = map;
        this.random = new Random();
    }

    public void spawnZombies(long elapsedTime) {
        int totalZombies = getTotalZombies();
        if (totalZombies >= MAX_ZOMBIES) return; // exception zombie udah max

        if (elapsedTime < 20 || elapsedTime > 160) {
            return; 
        }
        
        int width = map.size();
        int i = random.nextInt(width);
        if (random.nextDouble() < SPAWN_PROBABILITY) {
            Zombie zombie = createRandomZombie(i);
            if (zombie != null) {
                for (int x = map.get(i).size() - 1; x >= 0; x--) {
                    Tile spawnTile = map.get(i).get(x);
                    if (spawnTile instanceof SpawnTile) {
                        spawnTile.addZombie(zombie);
                        zombie.setLastMoveTime(elapsedTime);
                        break;
                    }
                }
            }
        }
    }

    private Zombie createRandomZombie(int row) {
        int zombieType = random.nextInt(11);
        Zombie zombie = null;
        switch (zombieType) {
            case 0: zombie = new NormalZombie(); break;
            case 1: zombie = new ConeheadZombie(); break;
            case 2: zombie = new BucketheadZombie(); break;
            case 3: zombie = new DuckyTubeZombie(); break;
            case 4: zombie = new ScreenDoorZombie(); break;
            case 5: zombie = new FootballZombie(); break;
            case 6: zombie = new PoleVaultingZombie(); break;
            case 7: zombie = new NewspaperZombie(); break;
            case 8: zombie = new DolphinRiderZombie(); break;
            case 9: zombie = new BungeeZombie(); break;
            default: zombie = new NormalZombie(); // Fallback to NormalZombie
        }
        // Ensure the zombie type matches the row type
        if ((row == 2 || row == 3) && !zombie.getIs_Aquatic()) {
            // If row is water and zombie is not aquatic, create an aquatic zombie
            int aquaticType = random.nextInt(2);
            if (aquaticType == 0) {
                zombie = new DolphinRiderZombie();
            } else {
                zombie = new DuckyTubeZombie();
            }
        } else if ((row != 2 && row != 3) && zombie.getIs_Aquatic()) {
            // If row is not water and zombie is aquatic, create a non-aquatic zombie
            zombie = new NormalZombie();
        }

        return zombie;

    }

    private int getTotalZombies() {
        int count = 0;
        for (List<Tile> row : map) {
            for (Tile tile : row) {
                count += tile.getZombie().size();
            }
        }
        return count;
    }
}

class MapMover {
    private List<List<Tile>> map;

    public MapMover(List<List<Tile>> map) {
        this.map = map;
    }

    public void moveZombies(long elapsedTime) {
        synchronized (map) {
            for (List<Tile> row : map) {
                for (Tile tile : row) {
                    List<Zombie> zombiesOnTile = tile.getZombie();
                    for (Zombie zombie : new ArrayList<>(zombiesOnTile)) {
                        zombie.resetDebuff();
                        if (elapsedTime - zombie.getLastMoveTime() >= zombie.getMove_Speed()) {
                            //get plant
                            Plant plant = null;
                            if (tile instanceof GroundTile){
                                GroundTile ground = (GroundTile) tile;
                                plant = ground.getPlant();
                            } else if (tile instanceof WaterTile){ //WaterTile
                                WaterTile water = (WaterTile) tile;
                                plant = (Plant) water.getLilypad();
                            } else {}
                            
                            if (plant == null){ //no plant blocking
                                moveZombieToNextTile(zombie, tile);
                                zombie.setLastMoveTime(elapsedTime);
                            }
                        }
                    }
                }
            }
        }
    }

    private void moveZombieToNextTile(Zombie zombie, Tile currentTile) {
        int x = currentTile.getX();
        int y = currentTile.getY();
        if (x > 0) { // Move zombie left if not at the edge
            Tile nextTile = map.get(y).get(x - 1);
            if ((!(zombie.getIs_Aquatic()) && !(nextTile instanceof WaterTile)) ||
                (zombie.getIs_Aquatic() && !(nextTile instanceof GroundTile))) {
                currentTile.removeZombie(zombie);
                nextTile.addZombie(zombie);
            }
        } else { // exit condition
        }
    }
}

public class Map implements TimeObserver {
    private final int length = 11;
    private final int width = 6;
    private List<List<Tile>> map;
    private Random random;
    private long lastSpawnTime = 0;

    public Map(GameTimer gameTimer) {
        map = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            List<Tile> row = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                if (j == 0) {
                    row.add(new BaseTile(j, i));
                } else if (j == length - 1) {
                    row.add(new SpawnTile(j, i));
                } else if (i == 2 || i == 3) {
                    row.add(new WaterTile(j, i));
                } else {
                    row.add(new GroundTile(j, i));
                }
            }
            map.add(row);
        }
        random = new Random();
        gameTimer.addObserver(this);
    }

    public void printMap() {
        synchronized (map) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < length; j++) {
                    if (map.get(i).get(j) instanceof BaseTile) {
                        System.out.print("B ");
                    } else if (map.get(i).get(j) instanceof SpawnTile) {
                        System.out.print("S ");
                    } else if (map.get(i).get(j) instanceof WaterTile) {
                        System.out.print("W ");
                    } else if (map.get(i).get(j) instanceof GroundTile) {
                        System.out.print("G ");
                    }
                    if (map.get(i).get(j) instanceof GroundTile && ((GroundTile)map.get(i).get(j)).getPlant() != null) {
                        System.out.print("P");
                    } else if (map.get(i).get(j) instanceof WaterTile && ((WaterTile)map.get(i).get(j)).getLilypad() != null) {
                        System.out.print("P");
                    }
                    List<Zombie> zombiesOnTile = map.get(i).get(j).getZombie();
                    if (!zombiesOnTile.isEmpty()) {
                        for (Zombie zombie : zombiesOnTile) {
                            System.out.print("Z");
                        }
                        // if (zombiesOnTile.size() > 1) {
                        //     System.out.print("Z");
                        // } else {
                        //     System.out.print("z");
                        // }
                        System.out.print(" ");
                    } else {
                        System.out.print(". "); // Empty tile representation
                    }
                }
                System.out.println();
            }
            // System.out.println("\n");
        }
    }

    //getMap
    public List<List<Tile>> getMap() {
        return map;
    }

    //getter
    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
    }

    @Override
    public void update(long elapsedTime) {
        if (elapsedTime - lastSpawnTime >= 3) {
            new MapSpawner(map).spawnZombies(elapsedTime);
            lastSpawnTime = elapsedTime;
        }
        if (elapsedTime % 1 == 0) {
            new MapMover(map).moveZombies(elapsedTime);
        }
<<<<<<< Updated upstream
        if (elapsedTime % 10 == 0) {
            printMap();
        }
=======
>>>>>>> Stashed changes
    }

    // public static void main(String[] args) {
    //     GameTimer timer = new GameTimer();
    //     Map map = new Map(timer);
    //     timer.start();
    // }

}