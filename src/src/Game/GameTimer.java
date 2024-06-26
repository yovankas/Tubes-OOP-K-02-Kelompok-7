package src.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// use observer design pattern to notify other classes about the elapsed time
// print elapsed time to the console
class GameTimer {
    private final Timer timer;
    private long startTime;
    private long elapsedTime;
    private final List<TimeObserver> observers; // List of observers

    // Constructor initializing a new Timer
    public GameTimer() {
        this.timer = new Timer();
        observers = new ArrayList<>();
    }
    // Add observer to the list
    public void addObserver(TimeObserver observer) {
        observers.add(observer);
    }

    // Start timer
    public void start() {
        startTime = System.currentTimeMillis(); // Get start time in ms
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis(); // Get current time in ms
                elapsedTime = (currentTime - startTime) / 1000; // Convert ms to seconds
                for (TimeObserver observer : observers) {
                    observer.update(elapsedTime); // Notify observer
                }
            }
        }, 0, 1000); // 0 delay, 1000ms interval
    }

    // Stop timer
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    // print time
    public void printTime() {
        System.out.println("Elapsed Time: " + elapsedTime + " seconds");
    }
}