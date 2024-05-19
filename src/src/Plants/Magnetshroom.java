package src.Plants;

import src.Plant;
import src.Zombie;

public class Magnetshroom extends Plant{
 
    public Magnetshroom() {
        super("Magnetshroom", 100, 0, 0, 25, -1, 10);
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
            if (zombie.getHas_Iron()){
                zombie.setHealth(0);
                this.setHealth(0);
            }             
        }
    } 

    // public static void main(String[] args) {
    //     Magnetshroom a = new Magnetshroom() ;
    //     ConeheadZombie b = new ConeheadZombie();
    //     NormalZombie c = new NormalZombie() ;
    //     a.attack(b); a.attack(c);
    //     b.printInfo(); c. printInfo();
    //     System.out.println(b.stillHasIron());
    // }
}
