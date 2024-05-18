package src.Game;
public class TimeDriver {
    public static void main(String[] args) {
        GameTimer timerExample = new GameTimer();
        timerExample.start();

        Sun Sun = new Sun(timerExample);
        // The Sun instance is now observing the TimerExample instance
    }
}