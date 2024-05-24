package src;

public abstract class Plant extends Creatures {
    private int range ;
    private final int cooldown ; //in seconds
    private final int cost ;
    private static long lastPlantedTime;
    private static boolean ready=true;

    public Plant(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown){
        super(name, health, attack_damage, attack_speed);
        this.cost = cost;
        this.range = range ;
        this.cooldown = cooldown ;
    }

    public void setLastPlantedTime() {
        lastPlantedTime = System.currentTimeMillis();
        ready = false;
    }

    public boolean isReady(){
        if ((System.currentTimeMillis() - lastPlantedTime)/1000 >= cooldown){
            ready = true;
        }
        return ready;
    }

    public int getCost() {
        return cost ;
    }

    public int getRange() {
        return range ;
    }

    public void setRange(int range) {
        this.range = range ;
    }

    public int getCooldown() {
        return cooldown ;
    }

    // Getter for lastMoveTime
    public long getLastPlantedTime() {
        return lastPlantedTime;
    }

    public abstract void attack(Zombie zombie);
}
