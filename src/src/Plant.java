package src;

public abstract class Plant extends Creatures {
    private final int range ;
    private final int cooldown ;
    private final int cost ;
    private static long lastPlantedTime;

    public Plant(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown){
        super(name, health, attack_damage, attack_speed);
        this.cost = cost;
        this.range = range ;
        this.cooldown = cooldown ;
    }

    private void setLastPlantedTime() {
        lastPlantedTime = System.currentTimeMillis();
    }

    private long getLastPlantedTime() {
        return lastPlantedTime;
    }

    public int getCost() {
        return cost ;
    }

    public int getRange() {
        return range ;
    }

    public int getCooldown() {
        return cooldown ;
    }

    // Getter for lastMoveTime
    public long getLastPlantedTime() {
        return lastPlantedTime;
    }

    // Setter for lastMoveTime
    public void setLastPlantedTime(long lastPlantedTime) {
        this.lastPlantedTime = lastPlantedTime;
    }


    public abstract void attack(Zombie zombie);
}
