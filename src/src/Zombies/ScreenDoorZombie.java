package src.Zombies;

import src.Zombie;

public class ScreenDoorZombie extends Zombie{
    private int screendoor_health = 180;

    public ScreenDoorZombie() {
        super("Screen Door Zombie", 125, 100, 1, 5, false);
    }

    public int getScreen_health() {
        return screendoor_health;
    }

    public void setScreen_health(int attack) {
        screendoor_health -= attack;
    }

    public boolean getIs_stillscreen() {
        return screendoor_health != 0;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Screen Health: " + getScreen_health());
    }
}
