package entities.cashDespenser.concreteCashDespenser;

import entities.cashDespenser.AbstractCashDespenser;
import entities.note.types.OneRupeeNote;

public class OneRupeeCashDespenser extends AbstractCashDespenser {

    public OneRupeeCashDespenser() {
        super(new OneRupeeNote());
    }

}
