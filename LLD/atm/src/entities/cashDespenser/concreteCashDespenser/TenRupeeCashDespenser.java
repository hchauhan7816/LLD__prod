package entities.cashDespenser.concreteCashDespenser;

import entities.cashDespenser.AbstractCashDespenser;
import entities.note.types.TenRupeeNote;

public class TenRupeeCashDespenser extends AbstractCashDespenser {

    public TenRupeeCashDespenser() {
        super(new TenRupeeNote());
    }

}
