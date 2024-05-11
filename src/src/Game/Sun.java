package src.Game;

import java.util.Random;

public class Sun implements TimeObserver {
    private static int amount = 25;
    private boolean isTaskScheduled = false;

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
        if ((elapsedTime/100)%2 == 0 && isTaskScheduled==false) { //daytime and no task scheduled
            //random delay between 5-10 seconds
            Random random = new Random(); 
            int delay = 5000 + random.nextInt(5000);

            //create new thread
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        isTaskScheduled = true;
                        Thread.sleep(delay); //delay
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt(); //IDK IF THIS NEEDED
                        e.printStackTrace(); //IDK IF THIS NEEDED
                    }
                    //after delay
                    increaseSunAmount(25); 
                    isTaskScheduled = false;
                }
            });
            thread.start();
        }
        System.out.print("\rSun: " + amount); //print sun (HANYA UNTUK TESTING)
    }
}