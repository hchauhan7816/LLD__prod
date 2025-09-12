package entities.state;

import entities.Card;
import entities.atmMachine.AtmMachine;

public interface AtmMachineState {
    void insertCard(AtmMachine atmMachine, Card card);

    void enterPin(AtmMachine atmMachine, String pin);

    void requestCash(AtmMachine atmMachine, Integer amount);
}
