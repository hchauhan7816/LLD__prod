package entities.atmMachine;

import entities.Card;

public class AtmMachineController {
    private final AtmMachine atmMachine;

    public AtmMachineController(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
        System.out.println("🎯 ATM Controller initialized");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public void insertCard(Card card) {
        System.out.println("\n🔵 User Action: Insert Card");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        atmMachine.getAtmMachineContext().insertCard(atmMachine, card);
    }

    public void enterPin(String pin) {
        System.out.println("\n🔵 User Action: Enter PIN");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        atmMachine.getAtmMachineContext().enterPin(atmMachine, pin);
    }

    public void requestCash(Integer amount) {
        System.out.println("\n🔵 User Action: Request Cash");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        atmMachine.getAtmMachineContext().requestCash(atmMachine, amount);
    }
    
    public void cancelTransaction() {
        System.out.println("\n🔵 User Action: Cancel Transaction");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        // Implementation for cancel transaction
    }
    
    public void ejectCard() {
        System.out.println("\n🔵 User Action: Eject Card");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        // Implementation for eject card
    }
    
    public String getCurrentStateDescription() {
        return atmMachine.getAtmMachineContext().getCurrentStateDescription();
    }
}
