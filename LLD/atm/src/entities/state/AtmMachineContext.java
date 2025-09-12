package entities.state;

import entities.Card;
import entities.atmMachine.AtmMachine;
import entities.state.states.IdleState;

public class AtmMachineContext {
    private AtmMachineState currentState;

    public AtmMachineContext() {
        this.currentState = new IdleState();
    }

    public void setCurrentState(AtmMachineState state) {
        this.currentState = state;
    }

    public void insertCard(AtmMachine atmMachine, Card card) {
        currentState.insertCard(atmMachine, card);
    }

    public void enterPin(AtmMachine atmMachine, String pin) {
        currentState.enterPin(atmMachine, pin);
    }

    public void requestCash(AtmMachine atmMachine, Integer amount) {
        currentState.requestCash(atmMachine, amount);
    }
    
    public String getCurrentStateDescription() {
        return currentState.getClass().getSimpleName();
    }
}
