package src.Plants;

import src.Plant;
import src.Zombie;

public class Wallnut extends Plant{
    
    public Wallnut() {
        super("Wallnut", 1000, 0, 0, 50, 0, 20);
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
