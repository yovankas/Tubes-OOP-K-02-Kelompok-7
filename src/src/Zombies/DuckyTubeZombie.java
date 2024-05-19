package src.Zombies;

import src.Plant;
import src.Zombie;

public class DuckyTubeZombie extends Zombie{

    public DuckyTubeZombie() {
        super("Ducky Tube Zombie", 100, 100, 1, 10, true, false, false);
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
