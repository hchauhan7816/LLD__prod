import entities.note.types.FiftyRupeeNote;
import entities.note.Note;
import java.util.HashMap;
import java.util.Map;

public class DebugNoteEquality {
    public static void main(String[] args) {
        System.out.println("🔍 DEBUGGING NOTE EQUALITY ISSUE");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        // Simulate what was happening in the ATM
        System.out.println("1️⃣ Creating notes in different places:");
        Note note1 = new FiftyRupeeNote(); // Created in AtmMachineFactory
        Note note2 = new FiftyRupeeNote(); // Created in App.java
        
        System.out.println("   note1 (Factory): " + note1 + " [hashCode: " + note1.hashCode() + "]");
        System.out.println("   note2 (App):     " + note2 + " [hashCode: " + note2.hashCode() + "]");
        
        System.out.println("\n2️⃣ Testing equality:");
        System.out.println("   note1 == note2: " + (note1 == note2));
        System.out.println("   note1.equals(note2): " + note1.equals(note2));
        
        System.out.println("\n3️⃣ Testing HashMap behavior:");
        Map<Note, Integer> cashTray = new HashMap<>();
        cashTray.put(note1, 10); // Put note1 in cash tray
        
        System.out.println("   cashTray.put(note1, 10)");
        System.out.println("   cashTray.get(note1): " + cashTray.get(note1));
        System.out.println("   cashTray.get(note2): " + cashTray.get(note2)); // ❌ This was returning null!
        
        System.out.println("\n4️⃣ The Problem:");
        System.out.println("   • AtmMachineFactory creates: new FiftyRupeeNote()");
        System.out.println("   • App.java creates: new FiftyRupeeNote()");
        System.out.println("   • These are DIFFERENT objects in memory");
        System.out.println("   • Without custom equals(), Java uses reference equality");
        System.out.println("   • So note1.equals(note2) returns false");
        System.out.println("   • HashMap.get(note2) returns null");
        
        System.out.println("\n5️⃣ The Solution:");
        System.out.println("   • Override equals() to compare by NoteTypeEnum");
        System.out.println("   • Override hashCode() to match equals()");
        System.out.println("   • Now note1.equals(note2) returns true");
        System.out.println("   • HashMap.get(note2) returns the correct value");
    }
} 