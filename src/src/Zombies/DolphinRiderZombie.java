package src.Zombies;

import src.Zombie;

public class DolphinRiderZombie extends Zombie {
    private boolean has_jump = false;

    public DolphinRiderZombie() {
        super("Dolphin Rider Zombie", 175, 100, 1, 5, true);
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
