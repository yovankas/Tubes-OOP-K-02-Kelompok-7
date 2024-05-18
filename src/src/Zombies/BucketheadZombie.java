package src.Zombies;

import src.IronZombie;
import src.Zombie;

public class BucketheadZombie extends Zombie implements IronZombie{
    private int iron_health = 175;

    public BucketheadZombie() {
        super("Buckethead Zombie", 300, 100, 1, 5, false);
    }

    public int getIron_health() {
        return iron_health;
    }

    public void setIron_health(int iron_health) {
        this.iron_health = iron_health ;
    }

    public boolean stillHasIron() {
        return iron_health > 0 ;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Bucket Health: " + getIron_health());
    }


}
