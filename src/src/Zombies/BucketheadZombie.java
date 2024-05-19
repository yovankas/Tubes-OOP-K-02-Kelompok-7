package src.Zombies;

import src.Plant;
import src.Zombie;

public class BucketheadZombie extends Zombie{
    private int shield = 175;

    public BucketheadZombie() {
        super("Buckethead Zombie", 300, 100, 1, 10, false, false, true);
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
        System.out.println("Bucket Health: " + getshield());
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

    // public static void main(String[] args) {
    //     BucketheadZombie z = new BucketheadZombie() ;
    //     Peashooter a = new Peashooter() ;
    //     z.attack(a);
    //     a.printInfo();
    //     for (int i= 0 ; i <7 ; i++) {
    //         a.attack(z);
    //     }
    //     a.attack(z);
    //     z.printInfo();
    // }
}
