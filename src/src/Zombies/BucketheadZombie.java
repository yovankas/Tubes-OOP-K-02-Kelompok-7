package src.Zombies;

import src.IronZombie;
import src.Zombie;
import src.Plant;
import src.Plants.*;

public class BucketheadZombie extends Zombie implements IronZombie{
    private int iron_health = 175;

    public BucketheadZombie() {
        super("Buckethead Zombie", 300, 100, 1, 10, false);
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

    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - attack_damage);
    }

    // public static void main(String[] args) {
    //     BucketheadZombie z = new BucketheadZombie() ;
    //     Peashooter a = new Peashooter() ;
    //     z.attack(a);
    //     a.printInfo();
    //     for (int i= 0 ; i <7 ; i++) {
    //         a.attack(z);
    //     }
    //     a.attack(z);
    //     z.printInfo();
    // }
}
