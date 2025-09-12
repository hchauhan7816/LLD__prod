package entities.state.states;

import entities.Card;
import entities.atmMachine.AtmMachine;
import entities.state.AtmMachineState;

public class IdleState implements AtmMachineState {

    @Override
    public void insertCard(AtmMachine atmMachine, Card card) {
        System.out.println("💳 Card inserted: " + card.getCardNumber());
        System.out.println("🔄 State transition: IDLE → HAS_CARD");
        atmMachine.setInsertedCard(card);
        atmMachine.getAtmMachineContext().setCurrentState(new HasCardState());
        System.out.println("✅ Please enter your PIN");
    }

    @Override
    public void enterPin(AtmMachine atmMachine, String pin) {
        System.out.println("❌ Cannot enter PIN: No card inserted");
        throw new UnsupportedOperationException("Please insert a card first");
    }

    @Override
    public void requestCash(AtmMachine atmMachine, Integer amount) {
        System.out.println("❌ Cannot request cash: No card inserted");
        throw new UnsupportedOperationException("Please insert a card and complete authentication first");
    }
}
