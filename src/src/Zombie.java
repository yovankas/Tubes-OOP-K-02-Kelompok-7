package src;

public abstract class Zombie extends Creatures {
    private double move_speed;
    private boolean is_aquatic;
    private long lastMoveTime;

    public Zombie(String name, int health, int attack_damage, int attack_speed, double move_speed, boolean is_aquatic){
        super(name, health, attack_damage, attack_speed);
        this.move_speed = move_speed;
        this.is_aquatic = is_aquatic;
    }

    public boolean getIs_Aquatic() {
        return is_aquatic;
    }

    public double getMove_Speed() {
        return move_speed;
    }
    
    public void setMove_Speed(double effect) {
        move_speed = move_speed * effect;
    }

    // Getter for lastMoveTime
    public long getLastMoveTime() {
        return lastMoveTime;
    }

    // Setter for lastMoveTime
    public void setLastMoveTime(long lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Move Speed: " + getMove_Speed());
        System.out.println("Is Aquatic: " + getIs_Aquatic());
    }

    public abstract void attack(Plant plant) ;
}
