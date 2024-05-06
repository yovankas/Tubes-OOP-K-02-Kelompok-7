package src.Zombies;

import src.Zombie;

public class NewspaperZombie extends Zombie{
    private int newspaper_health;

    public NewspaperZombie(String name, int health, int attack_damage, int attack_speed, double move_speed,
            boolean is_aquatic, int newspaper_health) {
        super(name, health, attack_damage, attack_speed, move_speed, is_aquatic);
        this.newspaper_health = newspaper_health;
    }

    public int getNewspaper_health() {
        return newspaper_health;
    }

    public void setNewsPap_health(int attack) {
        newspaper_health -= attack;
    }

    public boolean getIs_stillbucket() {
        return newspaper_health == 0;
    }
}
