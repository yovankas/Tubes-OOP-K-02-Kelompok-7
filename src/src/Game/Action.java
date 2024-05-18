package src.Game;

import java.util.List;

import src.Tile;

public class Action implements TimeObserver {
    private List<List<Tile>> map;

    //constructor
    public Action(GameTimer gameTimer, Map map) {
        gameTimer.addObserver(this);
        this.map = map.getMap();
    }

    @Override
    public void update(long elapsedTime) {

    }
}
