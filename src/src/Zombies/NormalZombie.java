package src.Zombies;

import src.Plant;
import src.Zombie;

public class NormalZombie extends Zombie{

    public NormalZombie() {
        super("Normal Zombie", 125, 100, 1, 10, false, false, false);
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
