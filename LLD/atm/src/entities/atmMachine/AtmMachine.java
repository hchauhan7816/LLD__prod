package entities.atmMachine;

import java.util.List;
import java.util.Map;

import entities.Account;
import entities.Card;
import entities.CashTray;
import entities.cashDespenser.CashDespenserManager;
import entities.cashDespenser.ICashDespenser;
import entities.note.Note;
import entities.state.AtmMachineContext;

public class AtmMachine {
    private AtmMachineContext atmMachineContext;
    private CashDespenserManager cashDespenserManager;

    private CashTray cashTray;
    private List<Account> accounts;

    private Card insertedCard;

    public AtmMachine(Map<Note, Integer> notes, List<Account> accounts, ICashDespenser cashDespenserChain) {
        this.cashTray = new CashTray(notes);
        this.accounts = accounts;

        this.atmMachineContext = new AtmMachineContext();
        this.cashDespenserManager = new CashDespenserManager(cashDespenserChain);

        this.insertedCard = null;
    }

    public AtmMachineContext getAtmMachineContext() {
        return atmMachineContext;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Card getInsertedCard() {
        return insertedCard;
    }

    public CashDespenserManager getCashDespenserManager() {
        return cashDespenserManager;
    }

    public CashTray getCashTray() {
        return cashTray;
    }

    public void setInsertedCard(Card card) {
        this.insertedCard = card;
    }
}
