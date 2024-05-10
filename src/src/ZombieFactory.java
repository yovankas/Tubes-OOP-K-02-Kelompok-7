package src;

import src.Zombies.BucketheadZombie;
import src.Zombies.ConeheadZombie;
import src.Zombies.DolphinRiderZombie;
import src.Zombies.DuckyTubeZombie;
import src.Zombies.FootballZombie;
import src.Zombies.NewspaperZombie;
import src.Zombies.NormalZombie;
import src.Zombies.PoleVaultingZombie;
import src.Zombies.ScreenDoorZombie;

public abstract class ZombieFactory {
    public abstract Zombie createZombie();
}

class BucketHeadFactory extends ZombieFactory {
    @Override
    public Zombie createZombie() {
        return new BucketheadZombie();
    }
}

class ConeheadFactory extends ZombieFactory {
    @Override
    public Zombie createZombie() {
        return new ConeheadZombie();
    }
}

class DolphinRiderFactory extends ZombieFactory {
    @Override
    public Zombie createZombie() {
        return new DolphinRiderZombie();
    }
}

class DuckyTubeFactory extends ZombieFactory {
    @Override
    public Zombie createZombie() {
        return new DuckyTubeZombie();
    }
}

class FootballFactory extends ZombieFactory {
    @Override
    public Zombie createZombie() {
        return new FootballZombie();
    }
}

class NewspaperFactory extends ZombieFactory {
    @Override
    public Zombie createZombie() {
        return new NewspaperZombie();
    }
}

class NormalFactory extends ZombieFactory {
    @Override
    public Zombie createZombie() {
        return new NormalZombie();
    }
}

class PoleVaultingFactory extends ZombieFactory {
    @Override
    public Zombie createZombie() {
        return new PoleVaultingZombie();
    }
}

class ScreenDoorFactory extends ZombieFactory {
    @Override
    public Zombie createZombie() {
        return new ScreenDoorZombie();
    }
}