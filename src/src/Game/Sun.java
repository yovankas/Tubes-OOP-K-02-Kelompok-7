package src.Game;

import java.util.Random;

public class Sun implements TimeObserver {
    private static int amount = 50;
    private int delay=0;

    public Sun(GameTimer timer) {
        timer.addObserver(this); // The Sun instance is now observing the GameTimer instance
    }

    public synchronized int getSun() {
        return amount;
    }

    public synchronized void decreaseSunAmount(int price) {
        amount -= price;
    }

    public synchronized void increaseSunAmount(int price) {
        amount += price;
    }

    //add sun amount every 10 second
    @Override
    public void update(long elapsedTime) {
        if ((elapsedTime/100)%2 == 0) { //daytime
            if (delay > 0) {
                delay--;
                if (delay == 0) {
                    increaseSunAmount(25);
                }
            } else {
                Random random = new Random(); 
                delay = 5 + random.nextInt(5);
            }
        }
    }

    public void printSun() {
        System.out.println("Sun: " + amount);
    }
}