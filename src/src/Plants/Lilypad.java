package src.Plants;

import src.Plant;
import src.Zombie;

public class Lilypad extends Plant {
    Plant plant;

    public Lilypad() {
        super("Lilypad", 100, 0, 0, 25, 0, 10);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }

    public void attack(Zombie zombie) {
        if (plant != null) {
            plant.attack(zombie);
        }
    } 

    public void addPlant(Plant plant) {
        this.plant = plant;
        this.setHealth(plant.getHealth()+this.getHealth());
    }

    public Plant getPlant() {
        return plant;
    }
}
