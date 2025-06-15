package com.example.classes;

import java.util.Objects;

public class Position {
    private final int row;
    private final int col;
    
    public Position(int row, int col) {
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Invalid position: (" + row + ", " + col + ")");
        }
        this.row = row;
        this.col = col;
    }
    
    public static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public String toChessNotation() {
        return String.valueOf((char)('a' + col)) + (8 - row);
    }
    
    public static Position fromChessNotation(String notation) {
        if (notation.length() != 2) {
            throw new IllegalArgumentException("Invalid chess notation: " + notation);
        }
        int col = notation.charAt(0) - 'a';
        int row = 8 - Character.getNumericValue(notation.charAt(1));
        return new Position(row, col);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
    
    @Override
    public String toString() {
        return "Position{" + toChessNotation() + "}";
    }
} 