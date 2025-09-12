import java.util.List;
import java.util.Map;
import java.util.HashMap;

import entities.Account;
import entities.Card;
import entities.atmMachine.AtmMachine;
import entities.atmMachine.AtmMachineController;
import entities.atmMachine.AtmMachineFactory;
import entities.note.types.FiftyRupeeNote;
import entities.note.types.OneRupeeNote;
import entities.note.types.TenRupeeNote;
import entities.note.types.TwentyRupeeNote;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("🏦 ==========================================");
        System.out.println("🏦    ATM MACHINE - DESIGN PATTERNS DEMO");
        System.out.println("🏦 ==========================================");
        System.out.println("📋 Patterns Used:");
        System.out.println("   • State Pattern: ATM State Management");
        System.out.println("   • Chain of Responsibility: Cash Dispensing");
        System.out.println("   • Factory Pattern: ATM Creation");
        System.out.println("   • Singleton Pattern: Factory Instance");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        AtmMachineFactory atmMachineFactory = AtmMachineFactory.getInstance();

        // Use HashMap instead of Map.of() to allow modifications
        Map<entities.note.Note, Integer> notes = new HashMap<>();
        notes.put(new FiftyRupeeNote(), 10);
        notes.put(new TwentyRupeeNote(), 10);
        notes.put(new TenRupeeNote(), 10);
        notes.put(new OneRupeeNote(), 10);

        AtmMachine atmMachine = atmMachineFactory.createAtmMachine(
                notes,
                List.of(
                        new Account("1234567890", 1000, "1234")));

        AtmMachineController atmMachineController = new AtmMachineController(atmMachine);

        // Demonstrate complete ATM transaction
        System.out.println("\n🎬 Starting ATM Transaction Demo...");
        
        atmMachineController.insertCard(new Card("1234567890"));
        atmMachineController.enterPin("1234");
        atmMachineController.requestCash(187);
        
        System.out.println("\n🏦 ==========================================");
        System.out.println("🏦    TRANSACTION COMPLETED SUCCESSFULLY");
        System.out.println("🏦 ==========================================");
    }
}
