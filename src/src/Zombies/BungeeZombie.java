package src.Zombies;

import src.Plant;
import src.Zombie;

public class BungeeZombie extends Zombie{

    public BungeeZombie() {
        super("Bungee Zombie", 200, 0, 1, 10, false, false, false);
    }

    public void attack(Plant plant) {
        // Tidak bisa attack, hanya bisa mencabut plant yang di depannya
        plant.setHealth(0);
        setHealth(0);
    }
    
}
