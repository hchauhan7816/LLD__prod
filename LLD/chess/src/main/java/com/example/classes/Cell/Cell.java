package com.example.classes.Cell;

import com.example.classes.Piece.Piece;
import com.example.classes.Position;
import com.example.enums.PieceColor;

public class Cell {
    private final Position position;
    private Piece piece;

    public Cell(Position position) {
        this.position = position;
        this.piece = null;
    }

    public Cell(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public boolean hasEnemyPiece(PieceColor color) {
        return piece != null && piece.getColor() != color;
    }

    public boolean hasFriendlyPiece(PieceColor color) {
        return piece != null && piece.getColor() == color;
    }

    public void removePiece() {
        this.piece = null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return position.toChessNotation() + ": Empty";
        }
        return position.toChessNotation() + ": " + piece.toString();
    }
}
