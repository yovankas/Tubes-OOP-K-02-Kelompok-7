package src;
public abstract class Zombie extends Creatures {
    private double move_speed;
    private boolean is_aquatic;
    private long lastMoveTime;
    private boolean has_jump = false;
    private boolean has_iron = false;
    private long lastSlowed;
    private int slowDuration;
    private boolean isSlowed = false;

    public Zombie(String name, int health, int attack_damage, int attack_speed, double move_speed, boolean is_aquatic, boolean has_jump, boolean has_iron){
        super(name, health, attack_damage, attack_speed);
        this.move_speed = move_speed;
        this.is_aquatic = is_aquatic;
        this.has_jump = has_jump;
        this.has_iron = has_iron;
    }

    public boolean getIs_Aquatic() {
        return is_aquatic;
    }

    public boolean getHas_Jump() {
        return has_jump;
    }

    public boolean getHas_Iron() {
        return has_iron;
    }

    public void setHas_Jump(boolean has_jump) {
        this.has_jump = has_jump;
    }

    public void setHas_Iron(boolean has_iron) {
        this.has_iron = has_iron;
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

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Move Speed: " + getMove_Speed());
        System.out.println("Is Aquatic: " + getIs_Aquatic());
    }

   public abstract void attack(Plant plant) ;

   public void slowDebuff(int slowDuration){
        lastSlowed = System.currentTimeMillis();
        this.slowDuration = slowDuration * 1000;
        isSlowed = true;
        if (!isSlowed){
            setMove_Speed(0.5);
            setAttack_Speed(getAttack_Speed()/2);
        }
   }

   public void resetDebuff(){
        if (isSlowed && System.currentTimeMillis() - lastSlowed >= slowDuration){
            isSlowed = false;
            setMove_Speed(2);
            setAttack_Speed(getAttack_Speed()*2);
        }
   }
}
