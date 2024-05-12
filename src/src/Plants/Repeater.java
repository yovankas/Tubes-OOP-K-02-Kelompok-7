package src.Plants;

import src.Plant;

public class Repeater extends Plant{
    
    public Repeater() {
        super("Repeater", 100, 50, 4, 150, -1, 10);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
}
