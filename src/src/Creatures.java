package src;


public abstract class Creatures {
    private final String name;
    private int health;
    protected int attack_damage;
    private int attack_speed;
    private final long timecreated;
    private long lastAttackTime = 0;

    public Creatures(String name, int health, int attack_damage, int attack_speed){
        this. name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.timecreated = System.currentTimeMillis();
    }

    public long getTimeCreated() {
        return timecreated;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setLastAttackTime(long lastAttackTime) {
        this.lastAttackTime = lastAttackTime;
    }

    public long getLastAttackTime() {
        return lastAttackTime;
    }

    public int getAttack_Damage() {
        return attack_damage;
    }

    public int getAttack_Speed() {
        return attack_speed;
    }

    public void setHealth(int health) {
        this.health = health ;
    }

    public void setAttack_Speed(int attack) {
        this.attack_speed = attack;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void printInfo() {
        System.out.println("Nama: " + getName());
        System.out.println("Health: " + getHealth());
        System.out.println("Attack Damage: " + getAttack_Damage());
        System.out.println("Attack Speed: " + getAttack_Speed());
    }
}