package src;

public class Sun {
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
}
