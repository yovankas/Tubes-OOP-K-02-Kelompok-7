package src.Zombies;

import src.Zombie;

public class BucketheadZombie extends Zombie{
    private int bucket_health = 175;

    public BucketheadZombie() {
        super("Buckethead Zombie", 300, 100, 1, 5, false);
    }

    public int getBucket_health() {
        return bucket_health;
    }

    public void setBucket_health(int attack) {
        bucket_health -= attack;
    }

    public boolean getIs_stillbucket() {
        return bucket_health != 0;
    }
}
