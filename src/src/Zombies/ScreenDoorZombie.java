package src.Zombies;

import src.Plant;
import src.Zombie;

public class ScreenDoorZombie extends Zombie {
    private int shield = 180;

    public ScreenDoorZombie() {
        super("Screen Door Zombie", 125, 100, 1, 10, false, false, true);
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
        if (getAttack_Speed() == 0){
            return;
        }
        if (getLastAttackTime() == 0 || (System.currentTimeMillis() - getLastAttackTime())/1000 >= getAttack_Speed()){
            setLastAttackTime(System.currentTimeMillis());
            plant.setHealth(plant.getHealth() - attack_damage);
        }
    }
}
