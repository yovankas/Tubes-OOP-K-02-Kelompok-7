package src.Plants;

import src.Plant;

public class Tallnut extends Plant {
    
    public Tallnut() {
        super("Tallnut", 1500, 0, 0, 100, 0, 25);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
}
