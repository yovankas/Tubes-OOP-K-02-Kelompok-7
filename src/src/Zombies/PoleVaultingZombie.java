package src.Zombies;

import src.Plant;
import src.Zombie;

public class PoleVaultingZombie extends Zombie{
    private boolean has_jump = false;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, 10, false);
    }

    public boolean getHas_jump() {
        return has_jump;
    }

    public void setHas_jump(boolean has_jump) {
        this.has_jump = has_jump;
    }

    public void jumpOver() {
        // Belum diisi
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Has jump: " + getHas_jump());
    }

    public void attack(Plant plant) {
        plant.setHealth(plant.getHealth() - attack_damage);
    }
}
