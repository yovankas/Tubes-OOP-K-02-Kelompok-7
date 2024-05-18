package src;

import src.Game.visitor;

public abstract class Plant extends Creatures {
    private int range ;
    private int cooldown ;
    private int cost ;

    public Plant(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown){
        super(name, health, attack_damage, attack_speed);
        this.cost = cost;
        this.range = range ;
        this.cooldown = cooldown ;
    }

    public int getCost() {
        return cost ;
    }

    public int getRange() {
        return range ;
    }

    public int getCooldown() {
        return cooldown ;
    }

    public abstract void attack(Zombie zombie) ;

    @Override
    public void accept(visitor visitor) {
        visitor.visitPlant(this);
    }
}
