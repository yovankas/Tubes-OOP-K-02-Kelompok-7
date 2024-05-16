package src.Plants;

import src.Plant;

public class Lilypad extends Plant {
    
    public Lilypad() {
        super("Lilypad", 100, 0, 0, 25, 0, 10);
    }
    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
}
