package src.Game;

import src.Plant;
import src.Zombie;

public interface visitor {
    public void visit(Plant plant);
    public void visit(Zombie zombie);
}
