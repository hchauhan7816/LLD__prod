package com.example.classes.Piece.Pieces;

import com.example.classes.Movement.Movements.RookMovementStrategy;
import com.example.classes.Piece.Piece;
import com.example.enums.PieceColor;
import com.example.enums.PieceType;

public class Rook extends Piece {
    
    public Rook(PieceColor color) {
        super(PieceType.ROOK, color, new RookMovementStrategy(color));
    }
}
