package src.Zombies;

import src.Zombie;

public class ConeheadZombie extends Zombie {
    private int cone_health = 125;

    public ConeheadZombie() {
        super("Conehead Zombie", 125, 100, 1, 5, false);
    }

    public int getCone_health() {
        return cone_health;
    }

    public void setBucket_health(int attack) {
        cone_health -= attack;
    }

    public boolean getIs_stillcone() {
        return cone_health != 0;
    }
}
