package vendingMachineState;

import vendingMachineState.states.IdleState;

public class VendingMachineContext {
    private IVendingMachineState currentState;

    public VendingMachineContext() {
        this.currentState = new IdleState();
    }

    public void advanceState() {
        IVendingMachineState nextState = currentState.next(this);
        currentState = nextState;
        System.out.println("Current state: " + currentState.getStateName());
    }

    public void setCurrentState(IVendingMachineState currentState) {
        this.currentState = currentState;
    }

}
