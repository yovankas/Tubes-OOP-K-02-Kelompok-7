package src.Plants;

import src.Plant;
import src.Zombie;


public class Peashooter extends Plant {
    
    public Peashooter() {
        super("Peashooter", 100, 25, 4, 100, -1, 10);
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

    // public static void main(String[] args) {
    //     Peashooter x = new Peashooter() ;
    //     NormalZombie y = new NormalZombie();
    // //    System.out.println(y.getHealth());
    //     x.attack(y);
    // //    System.out.println(y.getHealth());
    //     BucketheadZombie a = new BucketheadZombie() ;
    //     System.out.println(a.getIron_health());
    //     System.out.println(a.stillHasIron());
    //     x.attack(a);
    //     System.out.println(a.getIron_health());
    //     System.out.println(a.getHealth());
    //     a.setIron_health(25);
    //     x.attack(a);
    //     a.printInfo();
    //     x.attack(a);
    //     a.printInfo();
    // }
}
