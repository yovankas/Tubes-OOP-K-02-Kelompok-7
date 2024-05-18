package src.Zombies;

import src.IronZombie;
import src.Zombie;

public class ConeheadZombie extends Zombie implements IronZombie{
    private int iron_health = 125;

    public ConeheadZombie() {
        super("Conehead Zombie", 125, 100, 1, 5, false);
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
        System.out.println("Cone Health: " + getIron_health());
    }
}
