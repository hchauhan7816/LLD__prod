package entities;

public class Account {
    private String accountNumber;
    private Integer balance;
    private String pin;

    public Account(String accountNumber, Integer balance, String pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean validateCardAndPin(Card card, String pin) {
        if (this.pin.equals(pin) && this.accountNumber.equals(card.getCardNumber())) {
            return true;
        } else {
            return false;
        }
    }

    public void withdraw(Integer amount) {
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
