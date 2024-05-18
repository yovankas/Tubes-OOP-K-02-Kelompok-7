package src.Zombies;

import src.Plant;
import src.Zombie;

public class NormalZombie extends Zombie{

    public NormalZombie() {
        super("Normal Zombie", 125, 100, 1, 5, false);
    }

    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - attack_damage);
    }
}
