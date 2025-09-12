package entities.cashDespenser.concreteCashDespenser;

import entities.cashDespenser.AbstractCashDespenser;
import entities.note.types.FiftyRupeeNote;

public class FiftyRupeeCashDespenser extends AbstractCashDespenser {

    public FiftyRupeeCashDespenser() {
        super(new FiftyRupeeNote());
    }

}
