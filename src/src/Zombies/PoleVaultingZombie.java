package src.Zombies;

import src.Zombie;

public class PoleVaultingZombie extends Zombie{
    private boolean has_jump;

    public PoleVaultingZombie(String name, int health, int attack_damage, int attack_speed, double move_speed,
            boolean is_aquatic, boolean has_jump) {
        super(name, health, attack_damage, attack_speed, move_speed, is_aquatic);
        this.has_jump = has_jump;
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
}
