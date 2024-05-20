package src.Plants;

import src.Plant;
import src.Zombie;

public class Squash extends Plant {

    public Squash() {
        super("Squash", 100, 5000, 0, 50, 1, 20);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }

    @Override
    public void attack(Zombie zombie) {
        if (getAttack_Damage() == 0){
            return;
        }
        if (getAttack_Damage() == 0 || (System.currentTimeMillis() - getLastAttackTime())/1000 >= getAttack_Speed()){
            setLastAttackTime(System.currentTimeMillis());
            zombie.setHealth(zombie.getHealth() - attack_damage);
        }
    }
}
