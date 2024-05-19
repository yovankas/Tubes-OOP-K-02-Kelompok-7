package src.Plants;

import src.Plant;
import src.Zombie;

public class TwinSunflower extends Plant {
   
    public TwinSunflower() {
        super("TwinSunflower", 200, 0, 0, 100, 0, 10);
    }
 
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }

    @Override
    public void attack(Zombie zombie) {
        zombie.setHealth(zombie.getHealth() - attack_damage);
    } 
}