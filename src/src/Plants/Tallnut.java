package src.Plants;

import src.Plant;
import src.Zombie;

public class Tallnut extends Plant {
    
    public Tallnut() {
        super("Tallnut", 1500, 0, 0, 100, 0, 25);
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
