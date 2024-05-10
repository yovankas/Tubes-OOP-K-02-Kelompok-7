package src.Zombies;

import src.Zombie;

public class FootballZombie extends Zombie{
    private int helmet_health = 140;

    public FootballZombie() {
        super("Football Zombie", 125, 100, 1, 5, false);
    }

    public int getHelmet_health() {
        return helmet_health;
    }

    public void setHelmet_health(int attack) {
        helmet_health -= attack;
    }

    public boolean getIs_stillhelmet() {
        return helmet_health != 0;
    }
}
