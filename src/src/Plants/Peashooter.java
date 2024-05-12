package src.Plants;

import src.Plant;

public class Peashooter extends Plant {
    
    public Peashooter() {
        super("Peashooter", 100, 25, 4, 100, -1, 10);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }


}
