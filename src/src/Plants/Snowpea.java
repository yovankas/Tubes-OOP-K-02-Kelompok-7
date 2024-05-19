package src.Plants;


import src.Plant;
import src.Zombie;


public class Snowpea extends Plant {

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
        zombie.setHealth(zombie.getHealth() - attack_damage);
        zombie.slowDebuff(3);
    } 
}
