package src.Plants;


import src.Plant;
import src.Zombie;


public class Snowpea extends Plant {
    private static long lastPlantedTime;
    private static boolean ready=true;
    public Snowpea() {
        super("Snowpea", 100, 25, 4, 175, -1, 10);
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
            zombie.slowDebuff(3);
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
