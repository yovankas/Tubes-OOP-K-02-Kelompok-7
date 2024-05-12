package src.Plants;

import src.Plant;

public class Sunflower extends Plant {
   
    public Sunflower() {
        super("Sunflower", 100, 0, 0, 50, 0, 10);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
 
}
