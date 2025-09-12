package entities;

public class Coffee implements CoffeeDecorator {
    private double price;

    public Coffee(double price) {
        this.price = price;
    }

    @Override
    public double getCost() {
        return price;
    }
}
