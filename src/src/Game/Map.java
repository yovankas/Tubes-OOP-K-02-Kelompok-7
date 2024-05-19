package src.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import src.Plant;
import src.Tile;
import src.Zombie;
import src.Plants.*;
import src.Tiles.*;
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
        if (totalZombies >= MAX_ZOMBIES) return;

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
        int zombieType = random.nextInt(10);
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
                        if (elapsedTime - zombie.getLastMoveTime() >= zombie.getMove_Speed()) {
                            moveZombieToNextTile(zombie, tile);
                            zombie.setLastMoveTime(elapsedTime);
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
    private GameTimer gameTimer;
    private long lastSpawnTime = 0;

    public Map(Timer existingTimer) {
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
        gameTimer = new GameTimer(existingTimer);
        gameTimer.addObserver(this);
        gameTimer.start();
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
                    if (!map.get(i).get(j).getZombie().isEmpty()) {
                        System.out.print("Z ");
                    } else {
                        System.out.print(". "); // Empty tile representation
                    }
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }

    //getMap
    public List<List<Tile>> getMap() {
        return map;
    }

    @Override
    public void update(long elapsedTime) {
        if (elapsedTime - lastSpawnTime >= 1) {
            new MapSpawner(map).spawnZombies(elapsedTime);
            lastSpawnTime = elapsedTime;
            new MapMover(map).moveZombies(elapsedTime);
        }
        if (elapsedTime % 1 == 0) {
            printMap();
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        Map map = new Map(timer);
    }

}