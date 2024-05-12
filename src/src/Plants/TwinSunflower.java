package src.Plants;

import src.Plant;

public class TwinSunflower extends Plant {
   
    public TwinSunflower() {
        super("TwinSunflower", 200, 0, 0, 100, 0, 10);
    }
 
    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
}