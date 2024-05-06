package src.Zombies;

import src.Zombie;

public class BucketheadZombie extends Zombie{
    private int bucket_health;

    public BucketheadZombie(String name, int health, int attack_damage, int attack_speed, double move_speed,
            boolean is_aquatic, int bucket_health) {
        super(name, health, attack_damage, attack_speed, move_speed, is_aquatic);
        this.bucket_health = bucket_health;
    }

    public int getBucket_health() {
        return bucket_health;
    }

    public void setBucket_health(int attack) {
        bucket_health -= attack;
    }

    public boolean getIs_stillbucket() {
        return bucket_health == 0;
    }
}
