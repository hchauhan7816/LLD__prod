package com.example.classes.Piece.Pieces;

import com.example.classes.Movement.Movements.PawnMovementStrategy;
import com.example.classes.Piece.Piece;
import com.example.enums.PieceColor;
import com.example.enums.PieceType;

public class Pawn extends Piece {
    
    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color, new PawnMovementStrategy(color));
    }
}
