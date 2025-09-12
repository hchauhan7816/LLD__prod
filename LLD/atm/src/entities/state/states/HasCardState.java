package entities.state.states;

import entities.Card;
import entities.atmMachine.AtmMachine;
import entities.state.AtmMachineState;

public class HasCardState implements AtmMachineState {

    @Override
    public void insertCard(AtmMachine atmMachine, Card card) {
        System.out.println("❌ Cannot insert card: Card already inserted");
        throw new UnsupportedOperationException("Card already inserted. Please enter your PIN");
    }

    @Override
    public void enterPin(AtmMachine atmMachine, String pin) {
        System.out.println("🔐 Validating PIN for card: " + atmMachine.getInsertedCard().getCardNumber());
        
        if (atmMachine.getAccounts().stream()
                .anyMatch(account -> account.validateCardAndPin(atmMachine.getInsertedCard(), pin))) {
            System.out.println("✅ PIN validation successful");
            System.out.println("🔄 State transition: HAS_CARD → DISPENSE");
            atmMachine.getAtmMachineContext().setCurrentState(new DespenseState());
            System.out.println("💰 Please enter withdrawal amount");
        } else {
            System.out.println("❌ PIN validation failed");
            throw new RuntimeException("Invalid PIN. Please try again");
        }
    }

    @Override
    public void requestCash(AtmMachine atmMachine, Integer amount) {
        System.out.println("❌ Cannot request cash: PIN not entered");
        throw new UnsupportedOperationException("Please enter your PIN first");
    }
}
