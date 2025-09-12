package entities.decorators;

import entities.CoffeeDecorator;

public class SugarDecorator implements CoffeeDecorator {

    private CoffeeDecorator wrapee;

    public SugarDecorator(CoffeeDecorator wrapee) {
        this.wrapee = wrapee;
    }

    @Override
    public double getCost() {
        return wrapee.getCost() + 20;
    }
}
