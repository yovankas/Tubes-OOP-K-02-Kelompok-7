package src.Game;
import java.util.Timer;

//untuk testing yg berhubungan dengan time, sementara.
public class TimeDriver {
    public static void main(String[] args) {
        Timer timer = new Timer();
        GameTimer timerExample = new GameTimer(timer);
        timerExample.start();

        Sun Sun = new Sun(timerExample);
        // The Sun instance is now observing the TimerExample instance
    }
}