package entities.payment.strategies;

import entities.payment.PaymentStrategy;

public class CardStrategy implements PaymentStrategy {

    private String cardNumber;

    public CardStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment of " + amount + " done via Card");
    }

}
