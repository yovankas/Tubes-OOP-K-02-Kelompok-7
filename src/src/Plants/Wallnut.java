package src.Plants;

import src.Plant;

public class Wallnut extends Plant{
    
    public Wallnut() {
        super("Wallnut", 1000, 0, 0, 50, 0, 20);
    }
    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
}
