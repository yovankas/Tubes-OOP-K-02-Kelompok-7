package src;

public abstract class Creatures {
    private String name;
    private int health;
    private int attack_damage;
    private int attack_speed;

    public Creatures(String name, int health, int attack_damage, int attack_speed){
        this. name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack_Damage() {
        return attack_damage;
    }

    public int getAttack_Speed() {
        return attack_speed;
    }

    public void setHealth(int attack) {
        health -= attack;
    }

    public void setAttack_Speed(int attack) {
        this.attack_speed = attack;
    }
}