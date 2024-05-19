package src.Zombies;

import src.Plant;
import src.Zombie;

public class ConeheadZombie extends Zombie{
    private int shield = 125;

    public ConeheadZombie() {
        super("Conehead Zombie", 125, 100, 1, 10, false, false, true);
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
        System.out.println("Cone Health: " + getshield());
    }

    @Override
    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - attack_damage);
    }
}
