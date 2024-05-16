package src.Plants;

import src.Plant;

public class Snowpea extends Plant {

    public Snowpea() {
        super("Snowpea", 100, 25, 4, 175, -1, 10);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
    
}
