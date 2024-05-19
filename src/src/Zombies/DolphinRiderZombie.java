package src.Zombies;

import src.Plant;
import src.Zombie;

public class DolphinRiderZombie extends Zombie {

    public DolphinRiderZombie() {
        super("Dolphin Rider Zombie", 175, 100, 1, 10, true, true, false);
    }

    public void jumpOver() {
        // Belum diisi
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Has jump: " + getHas_Jump());
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - attack_damage);
    }
}
