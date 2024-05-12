package src.Plants;

import src.Plant;

public class Squash extends Plant {

    public Squash() {
        super("Squash", 100, 5000, 0, 50, 1, 20);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
}
