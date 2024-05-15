package src.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// use observer design pattern to notify other classes about the elapsed time
// print elapsed time to the console
public class GameTimer {
    private Timer timer;
    private long startTime;
    private long elapsedTime;
    private List<TimeObserver> observers; // list of observers

    // constructor
    public GameTimer() {
        timer = new Timer();
        observers = new ArrayList<>();
    }

    // Add observer to the list
    public void addObserver(TimeObserver observer) {
        observers.add(observer);
    }

    // start timer
    public void start() {
        startTime = System.currentTimeMillis(); // get start time in ms
        timer.schedule(new TimerTask() { //"TimerTask() {}" creating temporary class that extends TimerTask
            @Override // override run method
            public void run() {
                long currentTime = System.currentTimeMillis(); // get current time in ms
                elapsedTime = (currentTime - startTime) / 1000; //convert ms to seconds
                System.out.print("\rElapsed time: " + elapsedTime + " seconds"); // "/r" to overwrite the previous line
                for (TimeObserver observer : observers) {
                    observer.update(elapsedTime); // notify observer
                }
            }
        }, 0, 1000); //0 delay, 1000ms interval
    }

    // stop timer
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }
}