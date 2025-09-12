package vendingMachineState;

import enums.VendingMachineStatesEnum;

public interface IVendingMachineState {
    VendingMachineStatesEnum getStateName();

    public IVendingMachineState next(VendingMachineContext context);
}
