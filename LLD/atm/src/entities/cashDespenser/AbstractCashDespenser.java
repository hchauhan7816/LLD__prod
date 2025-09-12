package entities.cashDespenser;

import entities.atmMachine.AtmMachine;
import entities.note.Note;

public abstract class AbstractCashDespenser implements ICashDespenser {

    protected ICashDespenser nextCashDespenser;
    protected Note note;

    public AbstractCashDespenser(Note note) {
        this.note = note;
    }

    public Note getNote() {
        return note;
    }

    @Override
    public void setNextCashDespenser(ICashDespenser nextCashDespenser) {
        this.nextCashDespenser = nextCashDespenser;
    }

    @Override
    public void dispenseCash(AtmMachine atmMachine, Integer remainingAmount) {
        System.out.println("🔄 Processing $" + note.getNoteValue() + " denomination for amount: $" + remainingAmount);
        
        if (remainingAmount >= note.getNoteValue()) {
            int count = remainingAmount / note.getNoteValue();
            Integer noteCount = atmMachine.getCashTray().getNotes().get(note);

            if (noteCount == null) {
                System.out.println("❌ No $" + note.getNoteValue() + " notes available in cash tray");
                System.out.println("⏭️  Passing request to next handler...");
                
                if (nextCashDespenser != null) {
                    nextCashDespenser.dispenseCash(atmMachine, remainingAmount);
                } else {
                    System.out.println("⚠️  No more handlers available. Cannot dispense remaining amount: $" + remainingAmount);
                }
            } else if (noteCount < count) {
                // Can only dispense what we have
                int actualCount = noteCount;
                int dispensedAmount = actualCount * note.getNoteValue();
                int newRemainingAmount = remainingAmount - dispensedAmount;
                
                System.out.println("⚠️  Limited $" + note.getNoteValue() + " notes available: " + noteCount + " (requested: " + count + ")");
                System.out.println("💵 Dispensing " + actualCount + " x $" + note.getNoteValue() + " = $" + dispensedAmount);
                System.out.println("📊 Remaining amount to process: $" + newRemainingAmount);

                atmMachine.getCashTray().getNotes().put(note, 0);

                if (nextCashDespenser != null) {
                    nextCashDespenser.dispenseCash(atmMachine, newRemainingAmount);
                } else if (newRemainingAmount > 0) {
                    System.out.println("⚠️  No more handlers available. Cannot dispense remaining amount: $" + newRemainingAmount);
                }
            } else {
                // We have enough notes
                int dispensedAmount = count * note.getNoteValue();
                int newRemainingAmount = remainingAmount - dispensedAmount;
                
                System.out.println("✅ Sufficient $" + note.getNoteValue() + " notes available: " + noteCount + " (requested: " + count + ")");
                System.out.println("💵 Dispensing " + count + " x $" + note.getNoteValue() + " = $" + dispensedAmount);
                System.out.println("📊 Remaining amount to process: $" + newRemainingAmount);

                atmMachine.getCashTray().getNotes().put(note, noteCount - count);

                if (nextCashDespenser != null && newRemainingAmount > 0) {
                    nextCashDespenser.dispenseCash(atmMachine, newRemainingAmount);
                } else if (newRemainingAmount == 0) {
                    System.out.println("🎉 Cash dispensing completed successfully!");
                }
            }
        } else {
            // Amount is less than this note's value, pass to next handler
            System.out.println("⏭️  Amount $" + remainingAmount + " is less than $" + note.getNoteValue() + " denomination");
            System.out.println("⏭️  Passing request to next handler...");
            
            if (nextCashDespenser != null) {
                nextCashDespenser.dispenseCash(atmMachine, remainingAmount);
            } else {
                System.out.println("⚠️  No more handlers available. Cannot dispense amount: $" + remainingAmount);
            }
        }
    }
}
