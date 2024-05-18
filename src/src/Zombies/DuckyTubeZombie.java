package src.Zombies;

import src.Plant;
import src.Zombie;

public class DuckyTubeZombie extends Zombie{

    public DuckyTubeZombie() {
        super("Ducky Tube Zombie", 100, 100, 1, 5, true);
    }

    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - attack_damage);
    }
}
