package vendingMachineState.states;

import enums.VendingMachineStatesEnum;
import vendingMachineState.IVendingMachineState;
import vendingMachineState.VendingMachineContext;

public class IdleState implements IVendingMachineState {

    @Override
    public VendingMachineStatesEnum getStateName() {
        return VendingMachineStatesEnum.IDLE;
    }

    @Override
    public void next(VendingMachineContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }

}
