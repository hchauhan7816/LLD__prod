package com.example.classes.Piece.Pieces;

import com.example.classes.Movement.Movements.BishopMovementStrategy;
import com.example.classes.Piece.Piece;
import com.example.enums.PieceColor;
import com.example.enums.PieceType;

public class Bishop extends Piece {
    
    public Bishop(PieceColor color) {
        super(PieceType.BISHOP, color, new BishopMovementStrategy(color));
    }
}
