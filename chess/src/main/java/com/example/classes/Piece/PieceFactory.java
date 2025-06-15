package com.example.classes.Piece;

import com.example.classes.Piece.Pieces.*;
import com.example.enums.PieceColor;
import com.example.enums.PieceType;

public class PieceFactory {
    public static Piece createPiece(PieceType pieceType, PieceColor color) {
        switch (pieceType) {
            case KING:
                return new King(color);
            case QUEEN:
                return new Queen(color);
            case ROOK:
                return new Rook(color);
            case BISHOP:
                return new Bishop(color);
            case KNIGHT:
                return new Knight(color);
            case PAWN:
                return new Pawn(color);
            default:
                throw new IllegalArgumentException("Unknown piece type: " + pieceType);
        }
    }

    // Overloaded method for backward compatibility if needed
    public static Piece createPiece(String pieceType, PieceColor color) {
        return createPiece(PieceType.valueOf(pieceType.toUpperCase()), color);
    }
}
