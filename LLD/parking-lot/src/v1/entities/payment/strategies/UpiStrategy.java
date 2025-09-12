package entities.payment.strategies;

import entities.payment.PaymentStrategy;

public class UpiStrategy implements PaymentStrategy {
    private String upiId;

    public UpiStrategy(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment of " + amount + " done via UPI");
    }
}
