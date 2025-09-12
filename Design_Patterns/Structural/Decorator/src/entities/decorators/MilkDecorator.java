package entities.decorators;

import entities.CoffeeDecorator;

public class MilkDecorator implements CoffeeDecorator {

    private CoffeeDecorator wrapee;

    public MilkDecorator(CoffeeDecorator wrapee) {
        this.wrapee = wrapee;
    }

    @Override
    public double getCost() {
        return wrapee.getCost() + 50;
    }
}
