package entities.state.states;

import entities.Account;
import entities.Card;
import entities.atmMachine.AtmMachine;
import entities.state.AtmMachineState;

public class DespenseState implements AtmMachineState {

    @Override
    public void insertCard(AtmMachine atmMachine, Card card) {
        System.out.println("❌ Cannot insert card: Transaction in progress");
        throw new UnsupportedOperationException("Transaction in progress. Please wait");
    }

    @Override
    public void enterPin(AtmMachine atmMachine, String pin) {
        System.out.println("❌ Cannot enter PIN: Already authenticated");
        throw new UnsupportedOperationException("PIN already entered. Transaction in progress");
    }

    @Override
    public void requestCash(AtmMachine atmMachine, Integer amount) {
        System.out.println("💰 Processing withdrawal request: $" + amount);
        System.out.println("💳 Card: " + atmMachine.getInsertedCard().getCardNumber());
        
        if (atmMachine.getAccounts().stream().anyMatch(account -> account.getAccountNumber().equals(atmMachine.getInsertedCard().getCardNumber()))) {
            Account account = atmMachine.getAccounts().stream().filter(acc -> acc.getAccountNumber().equals(atmMachine.getInsertedCard().getCardNumber())).findFirst().get();
            
            System.out.println("📊 Account balance: $" + account.getBalance());
            
            if (account.getBalance() >= amount) {
                System.out.println("✅ Sufficient balance available");
                System.out.println("💸 Processing withdrawal...");
                
                account.withdraw(amount);
                System.out.println("📊 New balance: $" + account.getBalance());

                System.out.println("🏦 Initiating cash dispensing...");
                atmMachine.getCashDespenserManager().dispenseCash(atmMachine, amount);

                System.out.println("🔄 State transition: DISPENSE → IDLE");
                atmMachine.getAtmMachineContext().setCurrentState(new IdleState());
                System.out.println("🎉 Transaction completed successfully!");
                System.out.println("💳 Card ejected");
                atmMachine.setInsertedCard(null);
            } else {
                System.out.println("❌ Insufficient balance");
                System.out.println("📊 Required: $" + amount + ", Available: $" + account.getBalance());
                throw new RuntimeException("Insufficient balance. Please try a smaller amount");
            }
        } else {
            System.out.println("❌ Invalid account");
            throw new RuntimeException("Invalid account. Please contact support");
        }
    }
}
