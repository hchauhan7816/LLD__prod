package com.example.classes.Piece.Pieces;

import com.example.classes.Movement.Movements.QueenMovementStrategy;
import com.example.classes.Piece.Piece;
import com.example.enums.PieceColor;
import com.example.enums.PieceType;

public class Queen extends Piece {
    
    public Queen(PieceColor color) {
        super(PieceType.QUEEN, color, new QueenMovementStrategy(color));
    }
}
