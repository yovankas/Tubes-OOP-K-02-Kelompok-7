package src.Zombies;

import src.Plant;
import src.Zombie;

public class BungeeZombie extends Zombie{

    public BungeeZombie() {
        super("Bungee Zombie", 200, 0, 1, 10, false, false, false);
    }

    @Override
    public void attack(Plant plant) {
        // Tidak bisa attack, hanya bisa mencabut plant yang di depannya
        if (getAttack_Speed() == 0){
            return;
        }
        if (getLastAttackTime() == 0 || (System.currentTimeMillis() - getLastAttackTime())/1000 >= getAttack_Speed()){
            setLastAttackTime(System.currentTimeMillis());
            plant.setHealth(0);
            setHealth(0);
        }        
        
    }
    
}
