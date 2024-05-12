package src.Plants;

import src.Plant;

public class Magnetshroom extends Plant{
 
    public Magnetshroom() {
        super("Magnetshroom", 100, 0, 0, 25, -1, 10);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
}
