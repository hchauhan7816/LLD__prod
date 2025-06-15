package com.example.classes.Piece.Pieces;

import com.example.classes.Movement.Movements.KingMovementStrategy;
import com.example.classes.Piece.Piece;
import com.example.enums.PieceColor;
import com.example.enums.PieceType;

public class King extends Piece {
    
    public King(PieceColor color) {
        super(PieceType.KING, color, new KingMovementStrategy(color));
    }
}
