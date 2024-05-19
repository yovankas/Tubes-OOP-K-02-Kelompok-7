package src.Zombies;

import src.Plant;
import src.Zombie;

public class NewspaperZombie extends Zombie{
    private int newspaper_health = 150;

    public NewspaperZombie() {
        super("Newspaper Zombie", 125, 100, 1, 10, false);
    }

    public int getNewspaper_health() {
        return newspaper_health;
    }

    public void setNewsPap_health(int attack) {
        newspaper_health -= attack;
    }

    public boolean getIs_stillnewspap() {
        return newspaper_health != 0;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Newspaper Health: " + getNewspaper_health());
    }

    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - attack_damage);
    }
}
