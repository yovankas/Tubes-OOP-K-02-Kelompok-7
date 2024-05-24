package src.Plants;

import src.Plant;
import src.Zombie;

public class Squash extends Plant {
    private static long lastPlantedTime;
    private static boolean ready=true;
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
            setHealth(0);
        }
    }

    @Override
    public void setLastPlantedTime() {
        lastPlantedTime = System.currentTimeMillis();
        ready = false;
    }

    @Override
    public boolean isReady(){
        if ((System.currentTimeMillis() - lastPlantedTime)/1000 >= this.getCooldown()){
            ready = true;
        }
        return ready;
    }

    @Override
    public long getLastPlantedTime() {
        return lastPlantedTime;
    }
}
