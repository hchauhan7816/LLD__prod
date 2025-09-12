package entities.atmMachine;

import java.util.List;
import java.util.Map;

import entities.Account;
import entities.cashDespenser.concreteCashDespenser.FiftyRupeeCashDespenser;
import entities.cashDespenser.concreteCashDespenser.OneRupeeCashDespenser;
import entities.cashDespenser.concreteCashDespenser.TenRupeeCashDespenser;
import entities.cashDespenser.concreteCashDespenser.TwentyRupeeCashDespenser;
import entities.note.Note;

public class AtmMachineFactory {

    private static AtmMachineFactory instance;

    private AtmMachineFactory() {
    }

    public static AtmMachineFactory getInstance() {
        if (instance == null) {
            instance = new AtmMachineFactory();
        }
        return instance;
    }

    public AtmMachine createAtmMachine(Map<Note, Integer> notes, List<Account> accounts) {
        FiftyRupeeCashDespenser fifty = new FiftyRupeeCashDespenser();
        TwentyRupeeCashDespenser twenty = new TwentyRupeeCashDespenser();
        TenRupeeCashDespenser ten = new TenRupeeCashDespenser();
        OneRupeeCashDespenser one = new OneRupeeCashDespenser();

        fifty.setNextCashDespenser(twenty);
        twenty.setNextCashDespenser(ten);
        ten.setNextCashDespenser(one);

        return new AtmMachine(notes, accounts, fifty);
    }

}
