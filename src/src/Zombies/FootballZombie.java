package src.Zombies;

import src.Zombie;

public class FootballZombie extends Zombie{
    private int helmet_health;

    public FootballZombie(String name, int health, int attack_damage, int attack_speed, double move_speed,
            boolean is_aquatic, int helmet_health) {
        super(name, health, attack_damage, attack_speed, move_speed, is_aquatic);
        this.helmet_health = helmet_health;
    }

    public int getHelmet_health() {
        return helmet_health;
    }

    public void setHelmet_health(int attack) {
        helmet_health -= attack;
    }

    public boolean getIs_stillhelmet() {
        return helmet_health == 0;
    }
}
