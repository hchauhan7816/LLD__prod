package entities.cashDespenser;

import entities.atmMachine.AtmMachine;

public class CashDespenserManager {
    private ICashDespenser firstCashDespenser;

    public CashDespenserManager(ICashDespenser firstCashDespenser) {
        this.firstCashDespenser = firstCashDespenser;
    }

    public void dispenseCash(AtmMachine atmMachine, Integer amount) {
        firstCashDespenser.dispenseCash(atmMachine, amount);
    }
}
