package com.example.classes.Piece.Pieces;

import com.example.classes.Movement.Movements.KnightMovementStrategy;
import com.example.classes.Piece.Piece;
import com.example.enums.PieceColor;
import com.example.enums.PieceType;

public class Knight extends Piece {
    
    public Knight(PieceColor color) {
        super(PieceType.KNIGHT, color, new KnightMovementStrategy(color));
    }
}
