package src.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import src.Plant;
import src.Zombie;
import src.Plants.Lilypad;
import src.Zombies.*;

public class Map{
    private final int length=11;
    private final int width=6;
    private List<List<Tile>> map;
    private ScheduledExecutorService executor;
    private static final int MAX_ZOMBIES = 10;
    private static final double SPAWN_PROBABILITY = 0.3;
    private Random random;

    //constructor
    public Map(){
        map = new ArrayList<List<Tile>>();
        for(int i=0; i<width; i++){
            List<Tile> row = new ArrayList<Tile>();
            for(int j=0; j<length; j++){
                if (j == 0) {
                    row.add(new BaseTile(j, i));
                } else if (j == length-1) {
                    row.add(new SpawnTile(j, i));
                } else if (i == 2 || i == 3) {
                    row.add(new WaterTile(j, i));
                } else {
                    row.add(new GroundTile(j, i));
                }
            }
            map.add(row);
        }
        executor = Executors.newScheduledThreadPool(10); // Increase the pool size to handle multiple zombies
        random = new Random();
    }

    //getter
    public List<List<Tile>> getMap(){
        return map;
    }

    public int getLength(){
        return length;
    }

    public int getWidth(){
        return width;
    }

     // Add Zombie to SpawnTile
    public void addZombieToSpawn(Zombie zombie, int row) {
        Tile tile = map.get(row).get(length - 1); // SpawnTile is at the end of the row
        if (tile instanceof SpawnTile) {
            if ((!zombie.getIs_Aquatic() && !(tile instanceof WaterTile)) ||
                    (zombie.getIs_Aquatic() && (tile instanceof WaterTile))) {
                tile.addZombie(zombie);
                scheduleZombieMovement(zombie, tile);
            }
        }
    }

    // Schedule zombie movement
    private void scheduleZombieMovement(Zombie zombie, Tile currentTile) {
        Runnable moveTask = new Runnable() {
            private Tile tile = currentTile;

            @Override
            public void run() {
                synchronized (map) {
                    List<Zombie> zombiesToMove = tile.getZombie();
                    if (zombiesToMove.contains(zombie)) {
                        int x = tile.getX();
                        int y = tile.getY();
                        tile.removeZombie(zombie);

                        if (x > 0) { // Move zombie left if not at the edge
                            Tile nextTile = map.get(y).get(x - 1);
                            if ((!(zombie.getIs_Aquatic()) && !(nextTile instanceof WaterTile)) ||
                                (zombie.getIs_Aquatic() && nextTile instanceof WaterTile)) {
                                nextTile.addZombie(zombie);
                                tile = nextTile; // Update current tile
                            } else {
                                tile.addZombie(zombie); // Put the zombie back if it can't move to the next tile
                            }
                        } else {
                            tile.addZombie(zombie); // Put the zombie back if it can't move left --- NANTI DIBUAT EXCEPTION GAME SELESAI
                        }
                    }
                }
            }
        };

        executor.scheduleAtFixedRate(moveTask, 0, (long)zombie.getMove_Speed(), TimeUnit.SECONDS);
    }

    // Print Map
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

    // Spawn zombie based on probability
    private void spawnZombies() {
        int totalZombies = getTotalZombies();
        if (totalZombies >= MAX_ZOMBIES) return; // -- Exception zombie udah max

        for (int i = 0; i < width; i++) {
            if (random.nextDouble() < SPAWN_PROBABILITY) {
                Zombie zombie = createRandomZombie(i);
                if (zombie != null) {
                    addZombieToSpawn(zombie, i);
                }
            }
        }
    }

    // Create random zombie
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

    // Get total zombies on the map
    private int getTotalZombies() {
        int count = 0;
        for (List<Tile> row : map) {
            for (Tile tile : row) {
                count += tile.getZombie().size();
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Map map = new Map();
        
        ScheduledExecutorService printExecutor = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService spawnExecutor = Executors.newSingleThreadScheduledExecutor();

        // Print map every 5 seconds
        Runnable printTask = new Runnable() {
            @Override
            public void run() {
                map.printMap();
            }
        };

        // Spawn zombies every second
        Runnable spawnTask = new Runnable() {
            @Override
            public void run() {
                map.spawnZombies();
            }
        };

        printExecutor.scheduleAtFixedRate(printTask, 0, 5, TimeUnit.SECONDS);
        spawnExecutor.scheduleAtFixedRate(spawnTask, 0, 1, TimeUnit.SECONDS);
    }
}


class Tile{
    private int x;
    private int y;
    private ArrayList<Zombie> listOfZombies;

    //constructor
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        listOfZombies = new ArrayList<Zombie>();
    }

    //get zombie
    public ArrayList<Zombie> getZombie(){
        return listOfZombies;
    }

    // getters
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    //add zombie
    public void addZombie(Zombie zombie){
        listOfZombies.add(zombie);
    }

    //remove zombie
    public void removeZombie(Zombie zombie){
        listOfZombies.remove(zombie);
    }
}

class GroundTile extends Tile{
    private Plant plant;

    //constructor
    public GroundTile(int x, int y){
        super(x, y);
        this.plant = null;
    }

    //add plant
    public void addPlant(Plant plant){
        this.plant = plant;
    }

    //get plant
    public Plant getPlant(){
        return this.plant;
    }

    //remove plant
    public void removePlant(){
        this.plant = null;
    }
}

class WaterTile extends Tile{
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

class BaseTile extends Tile{
    //constructor
    public BaseTile(int x, int y){
        super(x, y);
    }
}

class SpawnTile extends Tile{
    //constructor
    public SpawnTile(int x, int y){
        super(x, y);
    }
}