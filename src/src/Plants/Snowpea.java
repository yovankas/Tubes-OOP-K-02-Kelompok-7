package src.Plants;

import src.IronZombie;
import src.Plant;
import src.Zombie;
import src.Zombies.*;

public class Snowpea extends Plant {

    public Snowpea() {
        super("Snowpea", 100, 25, 4, 175, -1, 10);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("cost: "+ getCost());
        System.out.println("Range: "+ getRange());
        System.out.println("Cooldown: "+ getCooldown());
    }
    
    public void attack(Zombie zombie) {
        if (zombie instanceof BucketheadZombie || zombie instanceof ConeheadZombie || zombie instanceof FootballZombie || zombie instanceof ScreenDoorZombie){
            if (((IronZombie) zombie).stillHasIron()) {
                ((IronZombie) zombie).setIron_health(((IronZombie) zombie).getIron_health() - attack_damage);
            } else {
                zombie.setHealth(zombie.getHealth() - attack_damage);
            }
        } else {
        zombie.setHealth(zombie.getHealth() - attack_damage);
        }
    } 
}
