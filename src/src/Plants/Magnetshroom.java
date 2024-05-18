package src.Plants;

import src.IronZombie;
import src.Plant;
import src.Zombie;
import src.Zombies.*;

public class Magnetshroom extends Plant{
 
    public Magnetshroom() {
        super("Magnetshroom", 100, 0, 0, 25, -1, 10);
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
                ((IronZombie) zombie).setIron_health(0);
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
