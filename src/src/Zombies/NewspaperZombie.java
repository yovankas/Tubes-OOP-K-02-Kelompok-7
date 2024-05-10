package src.Zombies;

import src.Zombie;

public class NewspaperZombie extends Zombie{
    private int newspaper_health = 150;

    public NewspaperZombie() {
        super("Newspaper Zombie", 125, 100, 1, 5, false);
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
}
