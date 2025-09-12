package entities;

import java.util.Map;

import entities.note.Note;

public class CashTray {
    private Map<Note, Integer> notes;

    public CashTray(Map<Note, Integer> notes) {
        this.notes = notes;
        System.out.println("🏦 Cash Tray initialized:");
        displayCashTrayStatus();
    }
    
    public void displayCashTrayStatus() {
        System.out.println("📋 Current Cash Tray Status:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (Map.Entry<Note, Integer> entry : notes.entrySet()) {
            System.out.println("   $" + entry.getKey().getNoteValue() + " notes: " + entry.getValue());
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public Map<Note, Integer> getNotes() {
        return notes;
    }
}
