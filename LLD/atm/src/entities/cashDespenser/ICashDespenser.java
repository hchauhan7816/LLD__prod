package entities.cashDespenser;

import entities.atmMachine.AtmMachine;

public interface ICashDespenser {

    void dispenseCash(AtmMachine atmMachine, Integer remainingAmount);

    void setNextCashDespenser(ICashDespenser nextCashDespenser);
}
