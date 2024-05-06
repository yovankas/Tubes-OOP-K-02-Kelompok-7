package src.Zombies;

import src.Zombie;

public class ScreenDoorZombie extends Zombie{
    private int screendoor_health;

    public ScreenDoorZombie(String name, int health, int attack_damage, int attack_speed, double move_speed,
            boolean is_aquatic, int screendoor_health) {
        super(name, health, attack_damage, attack_speed, move_speed, is_aquatic);
        this.screendoor_health = screendoor_health;
    }

    public int getScreen_health() {
        return screendoor_health;
    }

    public void setScreen_health(int attack) {
        screendoor_health -= attack;
    }

    public boolean getIs_stillscreen() {
        return screendoor_health == 0;
    }
}
