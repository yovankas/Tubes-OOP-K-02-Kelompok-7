package src.Plants;

import src.Plant;
import src.Zombie;

public class Repeater extends Plant{
    
    public Repeater() {
        super("Repeater", 100, 50, 4, 150, -1, 10);
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
        if (getAttack_Speed() == 0){
            return;
        }
        if (getLastAttackTime() == 0 || (System.currentTimeMillis() - getLastAttackTime())/1000 >= getAttack_Speed()){
            setLastAttackTime(System.currentTimeMillis());
            zombie.setHealth(zombie.getHealth() - attack_damage);
        }
    } 
}
