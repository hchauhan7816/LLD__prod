package entities.note;

import enums.NoteTypeEnum;

public abstract class Note {

    private final NoteTypeEnum note;

    public Note(NoteTypeEnum noteType) {
        this.note = noteType;
    }

    public int getNoteValue() {
        return note.getValue();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Note other = (Note) obj;
        return note == other.note;
    }
    
    @Override
    public int hashCode() {
        return note.hashCode();
    }
    
    @Override
    public String toString() {
        return "$" + note.getValue() + " Note";
    }
}
