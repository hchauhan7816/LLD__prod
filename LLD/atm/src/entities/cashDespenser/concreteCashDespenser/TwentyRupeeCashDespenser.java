package entities.cashDespenser.concreteCashDespenser;

import entities.cashDespenser.AbstractCashDespenser;
import entities.note.types.TwentyRupeeNote;

public class TwentyRupeeCashDespenser extends AbstractCashDespenser {

    public TwentyRupeeCashDespenser() {
        super(new TwentyRupeeNote());
    }

}
