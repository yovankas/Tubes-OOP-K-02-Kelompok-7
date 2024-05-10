package src;

public class Sun extends Thread {
    private int amount;

    public Sun(int amount) {
        this.amount = amount;
    }

    public int getSun() {
        return amount;
    }

    public void decreaseSunAmount(int price) {
        amount -= price;
    }

    public void increaseSunAmount(int price) {
        amount += price;
    }

    public void run() {
        try {
            Thread.sleep(5000); 
            increaseSunAmount(25); 
        } catch (InterruptedException e) {
        }
    }
}
