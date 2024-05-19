package src.Zombies;

import src.Plant;
import src.Zombie;

public class ScreenDoorZombie extends Zombie {
    private int shield = 180;

    public ScreenDoorZombie() {
        super("Screen Door Zombie", 125, 100, 1, 5, false, false, true);
    }

    public int getshield() {
        return shield;
    }

    public void setshield(int shield) {
        this.shield = shield ;
    }

    public boolean haveShield() {
        return shield > 0 ;
    }

    @Override
    public int getHealth() {
        if (haveShield()){
            return shield;
        } else {
            return super.getHealth();
        }
    }

    @Override
    public void setHealth(int health) {
        if (haveShield()){
            setshield(health);
            if (shield <= 0) {
                setHas_Iron(false);
            }
        } else {
            super.setHealth(health);
        }
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Screen Health: " + getshield());
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - attack_damage);
    }
}
